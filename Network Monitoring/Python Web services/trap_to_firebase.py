from firebase import firebase
from pysnmp.entity import engine, config
from pysnmp.carrier.asyncore.dgram import udp
from pysnmp.entity.rfc3413 import ntfrcv
import socket


firebase = firebase.FirebaseApplication('https://snmptrap-6f82c.firebaseio.com/', None)


def cbFun(snmpEngine, stateReference, contextEngineId, contextName,varBinds, cbCtx):
    global firebase
    #global_socket.send("test")
    print("Received new Trap message")
    data = {
        "DeviceName": str(varBinds[len(varBinds) - 1][1])
    }

    firebase.post("/Traps", data)


def trap_receiver():

    myAddress = socket.gethostbyname(socket.gethostname())
    snmpEngine = engine.SnmpEngine()
    TrapAgentAddress= myAddress
    Port=162
    config.addTransport(
    snmpEngine,
    udp.domainName + (1,),
    udp.UdpTransport().openServerMode((TrapAgentAddress, Port))
	)
    config.addV1System(snmpEngine, 'my-area', 'public')
    ntfrcv.NotificationReceiver(snmpEngine, cbFun)
    snmpEngine.transportDispatcher.jobStarted(1)
    try:
        snmpEngine.transportDispatcher.runDispatcher()
    except:
        snmpEngine.transportDispatcher.closeDispatcher()
        raise





trap_receiver()


# performans izleyicisi , olay görüntüleyicisi







"""
firebase = firebase.FirebaseApplication('https://snmptrap-6f82c.firebaseio.com/', None)
data = {
        "deneme": 1
    }

firebase.post("/test", data)
"""


