import requests
import json

license_code = "FGNlbGlrLXRyaWFsLWtlYnBmYm56BWNlbGlrHWNlbGlrLXRyaWFsLWtlYnBmYm56LWRldnJlY29nAAABcuF3H20xQLqL"


def get_license_status():
	endpoint = 'https://service.fing.io/2/2/devrecoglicense'
	r = requests.get(endpoint,headers={"accept":"application/json","X-Api-Key": license_code})
	return(r.json()['creditsavail'])
    

def get_dev_type(ip,mac):
	endpoint = 'https://service.fing.io/2/2/devrecog'
	request_string  =  {
	  "devices": [
		{
		  "ip": "192.168.1.200",
		  "mac": "48:88:CA:60:3B:83",
		  "nuid": "test-network",
		  "state": "UP"
		}
	  ],
	  "networks": [
		{
		  "nuid": "test-network",
		  "internetip": "93.48.247.65",
		  "gatewaymac": "00:11:22:33:44:55",
		  "dnsip": "192.168.0.1",
		  "netaddress": "192.168.0.1/24",
		  "gatewayip": "192.168.0.1"
		}
	  ]
	}
	
	

	
	
	rj = json.dumps(request_string)
	r = requests.post(endpoint,headers={"accept":"application/json","Content-Type": "application/json","X-Api-Key": license_code}, data = rj)
	
	print(r.json())
	


def main():
	remain = get_license_status()
	print(remain)
	get_dev_type(1,2)
	
main()	