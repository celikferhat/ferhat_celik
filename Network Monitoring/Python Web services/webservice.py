import nmap
from flask import Flask
from flask_cors import CORS
import json
import socket
import requests
from uuid import getnode as get_mac
from pysnmp.hlapi import *
import datetime


app = Flask(__name__)
CORS(app)






license_code = "FGNlbGlrLXRyaWFsLWtlYnBmYm56BWNlbGlrHWNlbGlrLXRyaWFsLWtlYnBmYm56LWRldnJlY29nAAABcuF3H20xQLqL"
@app.route('/get_local_ip')
def send_local_ip():
	myAddress = socket.gethostbyname(socket.gethostname())
	return str(myAddress)

def scan_nmap():

    
    myAddress = socket.gethostbyname(socket.gethostname())
    
    nm = nmap.PortScanner()
    response = nm.scan(hosts= myAddress + '/24',arguments='-sn')
    response2 = json.dumps(response)
    response3 = json.loads(response2)
    return json.dumps(response3)
	
def get_license_status():
	endpoint = 'https://service.fing.io/2/2/devrecoglicense'
	r = requests.get(endpoint,headers={"accept":"application/json","X-Api-Key": license_code})
	return(r.json()['creditsavail'])


def get_dev_type(scanresult):
	endpoint = 'https://service.fing.io/2/2/devrecog'

	ip = requests.get('https://api.ipify.org').text
	mac = get_mac()
	scan_dict = json.loads(scanresult)	
	uphosts = scan_dict["nmap"]["scanstats"]["uphosts"]	
	mac_string = ':'.join(("%012X" % mac)[i:i+2] for i in range(0, 12, 2))
	
	devices = []
	
	for i in scan_dict["scan"]:
		if(socket.gethostbyname(socket.gethostname()) == scan_dict["scan"][i]["addresses"]["ipv4"]):
			thisdict = {
			"ip": scan_dict["scan"][i]["addresses"]["ipv4"],
			"mac": mac_string,
			"nuid": "test-network",
			"state": "UP"
		}
		else:
			thisdict = {
				"ip": scan_dict["scan"][i]["addresses"]["ipv4"],
				"mac": scan_dict["scan"][i]["addresses"]["mac"],
				"nuid": "test-network",
				"state": "UP"
			}
		devices.append(thisdict)
		
		
		
	
	request_string  =  {
	  "devices": devices,
	  "networks": [
		{
		  "nuid": "test-network",
		  "internetip": ip,
		  "gatewaymac": scan_dict["scan"][next(iter(scan_dict["scan"]))]["addresses"]["mac"],
		  "dnsip": "192.168.1.1",
		  "netaddress": "192.168.1.1/24",
		  "gatewayip": "192.168.1.1"
		}
	  ]
	}

	
	rj = json.dumps(request_string)
	r = requests.post(endpoint,headers={"accept":"application/json","Content-Type": "application/json","X-Api-Key": license_code}, data = rj)
	
	recog = json.loads( json.dumps(r.json()) )	
	latest_format = []
	j=0
	for i in scan_dict["scan"] :
		type = ""
		brand = ""
		mac_vendor = ""
		model = ""
		os = ""
		if "recognition" in recog["devices"][j] :
		
			if "type" in recog["devices"][j]["recognition"]:
				type = recog["devices"][j]["recognition"]["type"]
			if "brand" in recog["devices"][j]["recognition"]:	
				brand = recog["devices"][j]["recognition"]["brand"]
			if "mac-vendor" in recog["devices"][j]["recognition"]:	
				mac_vendor = recog["devices"][j]["recognition"]["mac-vendor"]
			if "model" in recog["devices"][j]["recognition"]:		
				model = recog["devices"][j]["recognition"]["model"]
			if "os" in recog["devices"][j]["recognition"]:	
				os = recog["devices"][j]["recognition"]["os-name"]
				
		dvc = {
				"ip": devices[j]["ip"],
				"mac": devices[j]["mac"],
				"type": type,
				"brand": brand,
				"mac-vendor" : mac_vendor,
				"model" : model,
				"os" : os,
				"state": "UP"
			}
		j = j + 1
		latest_format.append(dvc)
	
	return(json.dumps(latest_format))







@app.route('/')
def welcome():
    return '{ "webservice":"nmap"}'

@app.route('/scan')
def scan():
	return(get_dev_type(scan_nmap()))
	#return ('[{"ip": "192.168.1.1", "mac": "F4:F2:6D:89:2C:20", "type": "WIFI", "brand": "TP-Link", "mac-vendor": "TP-Link", "model": "", "os": "", "state": "UP"}, {"ip": "192.168.1.100", "mac": "48:02:2D:01:B3:38", "type": "", "brand": "", "mac-vendor": "", "model": "", "os": "", "state": "UP"}, {"ip": "192.168.1.114", "mac": "00:0A:F5:11:41:EF", "type": "MOBILE", "brand": "Meizu", "mac-vendor": "Airgo Networks", "model": "M1852", "os": "", "state": "UP"}, {"ip": "192.168.1.112", "mac": "88:B4:A6:C1:AC:D0", "type": "MOBILE", "brand": "Motorola", "mac-vendor": "Motorola/Lenovo", "model": "", "os": "", "state": "UP"}, {"ip": "192.168.1.115", "mac": "30:A9:DE:45:03:96", "type": "", "brand": "", "mac-vendor": "LG Electronics", "model": "", "os": "", "state": "UP"}, {"ip": "192.168.1.116", "mac": "28:24:FF:79:93:3D", "type": "TELEVISION", "brand": "", "mac-vendor": "Wistron", "model": "", "os": "", "state": "UP"}, {"ip": "192.168.1.121", "mac": "70:85:C2:5E:23:89", "type": "", "brand": "", "mac-vendor": "ASRock", "model": "", "os": "", "state": "UP"}]')

@app.route('/snmp/sysdesk/<ip>')
def sysdesk(ip):	
	errorIndication, errorStatus, errorIndex, varBinds = next(
    getCmd(SnmpEngine(),
           CommunityData('public', mpModel=0),
           UdpTransportTarget((ip, 161)),
           ContextData(),
           ObjectType(ObjectIdentity('1.3.6.1.2.1.1.1.0'))) 
	)
	if errorIndication:
		return ""
	elif errorStatus:
		return ""
	else:
		return str(varBinds[0][1])
	

@app.route('/snmp/sysname/<ip>')
def sysname(ip):	
	errorIndication, errorStatus, errorIndex, varBinds = next(
    getCmd(SnmpEngine(),
           CommunityData('public', mpModel=0),
           UdpTransportTarget((ip, 161)),
           ContextData(),
           ObjectType(ObjectIdentity('1.3.6.1.2.1.1.5.0'))) 
	)
	if errorIndication:
		return ""
	elif errorStatus:
		return ""
	else:
		return str(varBinds[0][1])


@app.route('/snmp/sysuptime/<ip>')
def sysuptime(ip):	
	errorIndication, errorStatus, errorIndex, varBinds = next(
    getCmd(SnmpEngine(),
           CommunityData('public', mpModel=0),
           UdpTransportTarget((ip, 161)),
           ContextData(),
           ObjectType(ObjectIdentity('1.3.6.1.2.1.1.3.0'))) 
	)
	if errorIndication:
		return ""
	elif errorStatus:
		return ""
	else:		
		st = str(varBinds[0][1])[:-2]
		return str(datetime.timedelta( seconds = int(st) ))
		
@app.route('/snmp/sysifnum/<ip>')
def sysifnum(ip):	
	errorIndication, errorStatus, errorIndex, varBinds = next(
    getCmd(SnmpEngine(),
           CommunityData('public', mpModel=0),
           UdpTransportTarget((ip, 161)),
           ContextData(),
           ObjectType(ObjectIdentity('1.3.6.1.2.1.2.1.0'))) 
	)
	if errorIndication:
		return ""
	elif errorStatus:
		return ""
	else:
		return str(varBinds[0][1])		



@app.route('/snmp/sysprocess/<ip>')
def sysprocess(ip):	
	count = 0
	total = 0
	for (errorIndication,
		 errorStatus,
		 errorIndex,
		 varBinds) in nextCmd(SnmpEngine(),
							  CommunityData('public', mpModel=0),
							  UdpTransportTarget((ip, 161)),
							  ContextData(),
							  ObjectType(ObjectIdentity('1.3.6.1.2.1.25.3.3.1.2')),
							  lexicographicMode=False):

		if errorIndication:
			return "0"
			break
		elif errorStatus:
			return "0"
			break
		else:
			for varBind in varBinds:
				total += varBind[1] 
				count += 1
	
	return str(total / count)
			
			
@app.route('/snmp/sysram/<ip>')
def sysram(ip):	
	errorIndication, errorStatus, errorIndex, varBinds = next(
    getCmd(SnmpEngine(),
           CommunityData('public', mpModel=0),
           UdpTransportTarget((ip, 161)),
           ContextData(),
           ObjectType(ObjectIdentity('1.3.6.1.2.1.25.2.2.0'))) 
	)
	if errorIndication:
		return ""
	elif errorStatus:
		return ""
	else:
		return str(varBinds[0][1])				
			
@app.route('/snmp/sysavram/<ip>')
def sysavram(ip):	
	count = 0
	total = 0
	for (errorIndication,
		 errorStatus,
		 errorIndex,
		 varBinds) in nextCmd(SnmpEngine(),
							  CommunityData('public', mpModel=0),
							  UdpTransportTarget((ip, 161)),
							  ContextData(),
							  ObjectType(ObjectIdentity('1.3.6.1.2.1.25.5.1.1.2')),
							  lexicographicMode=False):

		if errorIndication:
			return "0"
			break
		elif errorStatus:
			return "0"
			break
		else:
			for varBind in varBinds:				
				total += varBind[1] 
				
			
	return str(total)
@app.route('/snmp/sysstorage/<ip>')
def sysstorage(ip):	
	count = 0
	total = 0
	array = []
	dcount = 0
	for (errorIndication,
		 errorStatus,
		 errorIndex,
		 varBinds) in nextCmd(SnmpEngine(),
							  CommunityData('public', mpModel=0),
							  UdpTransportTarget((ip, 161)),
							  ContextData(),
							  ObjectType(ObjectIdentity('1.3.6.1.2.1.25.2.3')),
							  lexicographicMode=False):

		if errorIndication:
			return "0"
			break
		elif errorStatus:
			return "0"
			break
		else:
			for varBind in varBinds:
				count += 1

				
				
				
	disk_num = count / 7
	
	disk_num = int(disk_num)
	count = 0
	for i in range(disk_num):
		dct = {
			"name" : "" ,
			"block": "",
			"avaible" : "" ,
			"used" : ""
		}
		array.append(dct)
	
	for (errorIndication,
		 errorStatus,
		 errorIndex,
		 varBinds) in nextCmd(SnmpEngine(),
							  CommunityData('public', mpModel=0),
							  UdpTransportTarget((ip, 161)),
							  ContextData(),
							  ObjectType(ObjectIdentity('1.3.6.1.2.1.25.2.3')),
							  lexicographicMode=False):

		if errorIndication:
			return "0"
			break
		elif errorStatus:
			return "0"
			break
		else:
			

				
				
			for varBind in varBinds:				
				if( disk_num * 2 <= dcount and  disk_num * 3 > dcount ):					
					array[dcount % disk_num]["name"] = str(varBind[1])
				elif(disk_num * 3 <= dcount and  disk_num * 4 > dcount):
					array[dcount % disk_num]["block"] = str(varBind[1])
				elif(disk_num * 4 <= dcount and  disk_num * 5 > dcount):
					array[dcount % disk_num]["avaible"] = str(varBind[1])
				elif(disk_num * 5 <= dcount and  disk_num * 6 > dcount):
					array[dcount % disk_num]["used"] = str(varBind[1])
					
					
				dcount += 1				
				
	return json.dumps(array)



@app.route('/snmp/sysprocesses/<ip>')
def sysprocesses(ip):	
	count = 0
	total = 0
	array = []
	dcount = 0
	for (errorIndication,
		 errorStatus,
		 errorIndex,
		 varBinds) in nextCmd(SnmpEngine(),
							  CommunityData('public', mpModel=0),
							  UdpTransportTarget((ip, 161)),
							  ContextData(),
							  ObjectType(ObjectIdentity('1.3.6.1.2.1.25.4.2')),
							  lexicographicMode=False):

		if errorIndication:
			return "0"
			break
		elif errorStatus:
			return "0"
			break
		else:
			for varBind in varBinds:
				count += 1

				
				
				
	disk_num = count / 7
	
	disk_num = int(disk_num)
	count = 0
	for i in range(disk_num):
		dct = {
			"name" : "" ,
			"path" : "" ,
			"parameter" : "" ,
			"run_type" : ""
		}
		array.append(dct)
	
	for (errorIndication,
		 errorStatus,
		 errorIndex,
		 varBinds) in nextCmd(SnmpEngine(),
							  CommunityData('public', mpModel=0),
							  UdpTransportTarget((ip, 161)),
							  ContextData(),
							  ObjectType(ObjectIdentity('1.3.6.1.2.1.25.4.2')),
							  lexicographicMode=False):

		if errorIndication:
			return "0"
			break
		elif errorStatus:
			return "0"
			break
		else:
			

				
				
			for varBind in varBinds:
				if( disk_num * 1 <= dcount and  disk_num * 2 > dcount ):					
					array[dcount % disk_num]["name"] = str(varBind[1])					
				elif(disk_num * 3 <= dcount and  disk_num * 4 > dcount):
					array[dcount % disk_num]["path"] = str(varBind[1])
				elif(disk_num * 4 <= dcount and  disk_num * 5 > dcount):
					array[dcount % disk_num]["parameter"] = str(varBind[1])
				elif(disk_num * 5 <= dcount and  disk_num * 6 > dcount):
					array[dcount % disk_num]["run_type"] = str(varBind[1])	
					
				dcount += 1				
				
	return json.dumps(array)	


@app.route('/snmp/sysdevice/<ip>')
def sysdevice(ip):	
	count = 0
	total = 0
	array = []
	dcount = 0
	for (errorIndication,
		 errorStatus,
		 errorIndex,
		 varBinds) in nextCmd(SnmpEngine(),
							  CommunityData('public', mpModel=0),
							  UdpTransportTarget((ip, 161)),
							  ContextData(),
							  ObjectType(ObjectIdentity('1.3.6.1.2.1.25.3.2')),
							  lexicographicMode=False):

		if errorIndication:
			return "0"
			break
		elif errorStatus:
			return "0"
			break
		else:
			for varBind in varBinds:
				count += 1

				
				
				
	disk_num = count / 6
	
	disk_num = int(disk_num)
	count = 0
	for i in range(disk_num):
		dct = {
			"name" : "" 			
		}
		array.append(dct)
	
	for (errorIndication,
		 errorStatus,
		 errorIndex,
		 varBinds) in nextCmd(SnmpEngine(),
							  CommunityData('public', mpModel=0),
							  UdpTransportTarget((ip, 161)),
							  ContextData(),
							  ObjectType(ObjectIdentity('1.3.6.1.2.1.25.3.2')),
							  lexicographicMode=False):

		if errorIndication:
			return "0"
			break
		elif errorStatus:
			return "0"
			break
		else:
			

				
				
			for varBind in varBinds:
				if( disk_num * 2 <= dcount and  disk_num * 3 > dcount ):					
					array[dcount % disk_num]["name"] = str(varBind[1])				
				
					
				dcount += 1				
				
	return json.dumps(array)	



@app.route('/snmp/sysprocnum/<ip>')
def sysprocnum(ip):	
	errorIndication, errorStatus, errorIndex, varBinds = next(
    getCmd(SnmpEngine(),
           CommunityData('public', mpModel=0),
           UdpTransportTarget((ip, 161)),
           ContextData(),
           ObjectType(ObjectIdentity('1.3.6.1.2.1.25.1.6.0'))) 
	)
	if errorIndication:
		return ""
	elif errorStatus:
		return ""
	else:
		return str(varBinds[0][1])


@app.route('/snmp/mobile/<ip>')
def mobile(ip):	
	array = []
	for i in range(4):
		dct = {
			"icon" : "" ,
			"text" : "" ,
			"context" : ""
		}
		array.append(dct)

	errorIndication, errorStatus, errorIndex, varBinds = next(
    getCmd(SnmpEngine(),
           CommunityData('public', mpModel=0),
           UdpTransportTarget((ip, 32150)),
           ContextData(),
           ObjectType(ObjectIdentity('1.3.6.1.4.1.12619.1.3.1.0'))) 
	)
	if errorIndication:
		return ""
	elif errorStatus:
		return ""
	else:
		
		if( int( varBinds[0][1] ) ==  0):
			array[0]["icon"] = "Discharge"
			array[0]["context"] = "Discharge"
		else:
			array[0]["icon"] = "Charge"
			array[0]["context"] = "Charge"
		array[0]["text"] = "Battery Status"
	
	
	
	
	errorIndication, errorStatus, errorIndex, varBinds = next(
    getCmd(SnmpEngine(),
           CommunityData('public', mpModel=0),
           UdpTransportTarget((ip, 32150)),
           ContextData(),
           ObjectType(ObjectIdentity('1.3.6.1.4.1.12619.1.3.2.0'))) 
	)
	if errorIndication:
		return ""
	elif errorStatus:
		return ""
	else:
		array[1]["icon"] = "Level"
		array[1]["text"] = "Battery Level"
		array[1]["context"] = str(varBinds[0][1])

	
	
	errorIndication, errorStatus, errorIndex, varBinds = next(
    getCmd(SnmpEngine(),
           CommunityData('public', mpModel=0),
           UdpTransportTarget((ip, 32150)),
           ContextData(),
           ObjectType(ObjectIdentity('1.3.6.1.4.1.12619.1.3.4.0'))) 
	)
	if errorIndication:
		return ""
	elif errorStatus:
		return ""
	else:
		array[2]["icon"] = "Bluetooth"
		array[2]["text"] = "Bluetooth Status"
		if(int(varBinds[0][1]) == 0):
			array[2]["context"] = "OFF"
		else:
			array[2]["context"] = "ON"	

	
	errorIndication, errorStatus, errorIndex, varBinds = next(
    getCmd(SnmpEngine(),
           CommunityData('public', mpModel=0),
           UdpTransportTarget((ip, 32150)),
           ContextData(),
           ObjectType(ObjectIdentity('1.3.6.1.4.1.12619.1.3.5.0'))) 
	)
	if errorIndication:
		return ""
	elif errorStatus:
		return ""
	else:
		array[3]["icon"] = "Wifi"
		array[3]["text"] = "Network Status"
		if(int(varBinds[0][1]) == 0):
			array[3]["context"] = "OFF"
		else:
			array[3]["context"] = "ON"	

	
	
	return json.dumps(array)





@app.route('/snmp/mobile/uptime/<ip>')
def sysmobileuptime(ip):	
	errorIndication, errorStatus, errorIndex, varBinds = next(
    getCmd(SnmpEngine(),
           CommunityData('public', mpModel=0),
           UdpTransportTarget((ip, 32150)),
           ContextData(),
           ObjectType(ObjectIdentity('1.3.6.1.4.1.12619.1.1.3.0'))) 
	)
	if errorIndication:
		return ""
	elif errorStatus:
		return ""
	else:
		#return datetime.fromtimestamp(int(str(varBinds[0][1]))).strftime("%A, %B %d, %Y %I:%M:%S") 
		st = str(varBinds[0][1])[:-2]
		return str(datetime.timedelta( seconds = int(st) ))

@app.route('/snmp/mobile/andver/<ip>')
def sysandver(ip):	
	errorIndication, errorStatus, errorIndex, varBinds = next(
    getCmd(SnmpEngine(),
           CommunityData('public', mpModel=0),
           UdpTransportTarget((ip, 32150)),
           ContextData(),
           ObjectType(ObjectIdentity('1.3.6.1.4.1.12619.1.1.2.0'))) 
	)
	if errorIndication:
		return ""
	elif errorStatus:
		return ""
	else:		
		return str(varBinds[0][1])

@app.route('/snmp/mobile/model/<ip>')
def sysmodel(ip):	
	errorIndication, errorStatus, errorIndex, varBinds = next(
    getCmd(SnmpEngine(),
           CommunityData('public', mpModel=0),
           UdpTransportTarget((ip, 32150)),
           ContextData(),
           ObjectType(ObjectIdentity('1.3.6.1.4.1.12619.1.1.1.0'))) 
	)
	if errorIndication:
		return ""
	elif errorStatus:
		return ""
	else:		
		return str(varBinds[0][1])



def main():
	import os
	HOST = os.environ.get('SERVER_HOST','localhost')
	PORT = 5000
	try:
		POST = int(os.environ.get('SERVER_PORT','5555'))
	except ValueError:
		PORT = 5555
	app.run(HOST,PORT)
	
main()	