import React, { useState } from 'react';
// react plugin for creating charts

// @material-ui/core
import { makeStyles } from "@material-ui/core/styles";
import Icon from "@material-ui/core/Icon";
// @material-ui/icons



// core components
import GridItem from "components/Grid/GridItem.js";
import GridContainer from "components/Grid/GridContainer.js";

import Card from "components/Card/Card.js";
import CardHeader from "components/Card/CardHeader.js";
import CardIcon from "components/Card/CardIcon.js";
import CardBody from "components/Card/CardBody.js";
import CardFooter from "components/Card/CardFooter.js";


import ReactLoading from "react-loading";


import {
  CircularProgressbar,
  CircularProgressbarWithChildren,
  buildStyles
} from "react-circular-progressbar";
import "react-circular-progressbar/dist/styles.css";
import RadialSeparators from "components/ProgressBar/RadialSeparators";


import ExpandableTable from "components/Table/ExpandableTable.js"


import styles from "assets/jss/material-dashboard-react/views/dashboardStyle.js";
import Success from "components/Typography/Success";
import Warning from "@material-ui/icons/Warning";
import Info from "@material-ui/icons/Info";
import {} from "getlocalip.js" 
import { Modal , Container , Row ,Table, Col  , Tabs , Tab } from 'react-bootstrap';

import Button from "components/CustomButtons/Button.js";
import ReactSpeedometer from "react-d3-speedometer"
import Storage from "components/Storage/Storage"
import Processes from "components/Processes/Processes"
import Mobile from "components/Mobile/Mobile"

import Trap from "components/Fire/Traps"



const useStyles = makeStyles(styles);



export default function Dashboard() {
  const classes = useStyles();

  const publicIp = require('public-ip');
  const [ip_address_v4,setv4_ip] = useState("N/A");
  const [ip_address_v6,setv6_ip] = useState("N/A");
  const [devicesArray,set_lan_array] = useState([]);
  const [local_ip_address,set_local_ip_address] = useState("N/A");
  fetch("http://127.0.0.1:5000/get_local_ip").then(res => res.text() ).then( (data) => {
    set_local_ip_address(data);
    
  } ).catch(error => {
    console.error('Error:', error);
    alert("Snmp service unavaible!");
  });

  (async () => {    
    const ip4 = await publicIp.v4();
    
    setv4_ip(ip4);
    
    //=> 'fe80::200:f8ff:fe21:67cf'
})()



function scan_lan(){
  document.getElementById("loading_item").style.visibility = "visible";
  document.getElementById("scan_button").style.display = "none";

  fetch("http://127.0.0.1:5000/scan").then(res => res.json()).then((data) => {
    
    document.getElementById("loading_item").style.visibility = "hidden";
    document.getElementById("scan_button").style.display = "block";
      
    var temp = Object.values(  Object.values(data) )
    var temp_array = []
	var iconpic = "GENERIC"
	
	
    temp.forEach(element => {
      if(String( element['type']).localeCompare("") != 0 ){
		  iconpic = String( element['type']);
	  }else{
		  iconpic = "GENERIC"
	  }
	  
      temp_array.push( { 
        status : String( element['state']) ,
        name:    String( element['brand']),
        ip:      String( element['ip']),
        mac:     String( element['mac']),
		type:    String(iconpic),
		vendor:	 String( element['mac-vendor']),
		os:		 String( element['os']),
		model:	 String( element['model']),
		icon : String(iconpic)
		}
      )
      
    }
      
      
      );
      


   // var temp_array = []
    //temp_array.push(["a","b","c","d","e"]);
    set_lan_array(temp_array);
  
  }).catch(error => {
  console.error('Error:', error);
  alert("Service unavaible!");
  document.getElementById("loading_item").style.visibility = "hidden";
    document.getElementById("scan_button").style.display = "block";
});




}

function get_memory(ip_address){

  fetch("http://127.0.0.1:5000/snmp/sysram/" + ip_address).then(res => res.text() ).then( (data) => {
    setmem( Math.ceil(parseInt(data) / (1024 * 1024))   );  
    console.log(memory_);
  } ).catch(error => {
    console.error('Error:', error);
    alert("Snmp service unavaible!");
  });


}

function get_cpu(ip_address){

  fetch("http://127.0.0.1:5000/snmp/sysprocess/" + ip_address).then(res => res.text() ).then( (data) => {
    setcpu( parseInt(data)  );      
  } ).catch(error => {
    console.error('Error:', error);
    alert("Snmp service unavaible!");
  });


}

function get_ram(ip_address){

  fetch("http://127.0.0.1:5000/snmp/sysavram/" + ip_address).then(res => res.text() ).then( (data) => {
    setram( parseInt(data)  );      
  } ).catch(error => {
    console.error('Error:', error);
    alert("Snmp service unavaible!");
  });


}

function get_storage(ip_address){
  var temp_array = []
  fetch("http://127.0.0.1:5000/snmp/sysstorage/" + ip_address).then(res => res.json() ).then( (data) => {
    document.getElementById("loading_item_disk").style.display = "none";
    data.forEach(element => {
      temp_array.push({
        name : element["name"],
        total : element["avaible"],
        used : element["used"],
        block : element["block"]
      });
    });
    
    set_storage_array(temp_array);
    
    
    set_sflag(true);
  } ).catch(error => {
    console.error('Error:', error);
    alert("Snmp service unavaible!");
  });


}


function get_process_list(ip_address){
  var temp_array = []
  fetch("http://127.0.0.1:5000/snmp/sysprocesses/" + ip_address).then(res => res.json() ).then( (data) => {
    document.getElementById("loading_item_process").style.display = "none";
    data.forEach(element => {
      temp_array.push({
        name : element["name"],
        path : element["path"],
        parameter : element["parameter"],
        run_type : element["run_type"],
      });
    });
    
    set_processes_array(temp_array);
    
    
    set_procflag(true);
  } ).catch(error => {
    console.error('Error:', error);
    alert("Snmp service unavaible!");
  });


}


function get_process(ip_address){

  fetch("http://127.0.0.1:5000/snmp/sysprocnum/" + ip_address).then(res => res.text() ).then( (data) => {
    setprocess( parseInt(data)  );      
  } ).catch(error => {
    console.error('Error:', error);
    alert("Snmp service unavaible!");
  });


}


function get_device_list(ip_address){
  var temp_array = []
  fetch("http://127.0.0.1:5000/snmp/sysdevice/" + ip_address).then(res => res.json() ).then( (data) => {
    document.getElementById("loading_item_device").style.display = "none";
    data.forEach(element => {
      temp_array.push({
        name : element["name"]        
      });
    });
    
    set_device_array(temp_array);
    
    
    set_deviceflag(true);
  } ).catch(error => {
    console.error('Error:', error);
    alert("Snmp service unavaible!");
  });


}



function get_mobile_info(ip_address){
  var temp_array = []
  
  fetch("http://127.0.0.1:5000/snmp/mobile/" + ip_address).then(res => res.json() ).then( (data) => {
    
    data.forEach(element => {
      temp_array.push({
        icon : element["icon"],
        text : element["text"],
        context : element["context"]
      });
    });
    
    set_mobile_array(temp_array);   
    set_mobileflag(true);

  } ).catch(error => {
    console.error('Error:', error);
    alert("Snmp service unavaible!");
  });

  fetch("http://127.0.0.1:5000/snmp/mobile/model/" + ip_address).then(res => res.text() ).then( (data) => {
    
        
    set_model(data);   
    

  } ).catch(error => {
    console.error('Error:', error);
    alert("Snmp service unavaible!");
  });

  fetch("http://127.0.0.1:5000/snmp/mobile/andver/" + ip_address).then(res => res.text() ).then( (data) => {
    
        
    set_andver(data);   
    

  } ).catch(error => {
    console.error('Error:', error);
    alert("Snmp service unavaible!");
  });
  fetch("http://127.0.0.1:5000/snmp/mobile/uptime/" + ip_address).then(res => res.text() ).then( (data) => {
    
        
    set_uptime(data);   
    

  } ).catch(error => {
    console.error('Error:', error);
    alert("Snmp service unavaible!");
  });

}



const isLoading = null;
const [show, setShow] = useState(false);
const [ip, setIP] = useState("");
const [memory_, setmem] = useState("");
const [ram, setram] = useState("");
const [cpu, setcpu] = useState("");
const [storageArray,set_storage_array] = useState([]);
const [storage_flag , set_sflag] = useState(false);

const [processesArray,set_processes_array] = useState([]);
const [processes_flag , set_procflag] = useState(false);

const [deviceArray,set_device_array] = useState([]);
const [device_flag , set_deviceflag] = useState(false);

const [process_num, setprocess] = useState(0);
const [key, setKey] = useState('storage');
const handleClose = () => setShow(false);
const handleShow = (ip_address) => {  
  setShow(true); 
  setIP(ip_address);
  setmem(0); 
  setcpu(0);
  setram(0); 
  set_storage_array([]);
  setprocess(0);
  set_processes_array([]);
  set_procflag(false);
  set_device_array([]);
  set_deviceflag(false); }
const get_infos  = (ip_address) => {
  document.getElementById("loading_item_disk").style.visibility = "visible";
  document.getElementById("loading_item_process").style.visibility = "visible";
  document.getElementById("loading_item_device").style.visibility = "visible";
   get_memory(ip_address);
   get_ram(ip_address);
   get_storage(ip_address);
  
   get_cpu(ip_address);
   get_process(ip_address);
   get_process_list(ip_address);
  // document.getElementById("loading_item_process").style.visibility = "hidden";
   get_device_list(ip_address); 
  // document.getElementById("loading_item_device").style.visibility = "hidden";
  
  }



const [show_mobile, setShow_mobile] = useState(false);
const [model, set_model] = useState("");
const [andver, set_andver] = useState("");
const [uptime, set_uptime] = useState("");
const [mobile_flag , set_mobileflag] = useState(false);
const [mobileArray,set_mobile_array] = useState([]);
const handleShow_mobile = (ip_address) => { setIP(ip_address); setShow_mobile(true);  set_model(""); set_andver(""); set_uptime("");set_mobile_array([]); get_mobile_info(ip_address); }
const handleClose_mobile = () => setShow_mobile(false);
  return (
    <div>	
      <Trap></Trap>
      <GridContainer>
        <GridItem xs={12} sm={6} md={3}>
          <Card>
            <CardHeader color="success" stats icon>
              <CardIcon color="success">
                <Icon>location_on</Icon>
              </CardIcon>
              <p className={classes.cardCategory}>IP Address</p>
              <h4 className={classes.cardTitle}>
              <small>Public IP:</small>  {ip_address_v4} <br></br>
              <small>Ipv6:     </small>  {ip_address_v6}
              </h4>
            </CardHeader>
            <CardFooter stats>
              <div className={classes.stats}>
                <Success>
                  <Info />
                </Success>
                <a href="#pablo" onClick={e => e.preventDefault()}>
                  Now
                </a>
              </div>
            </CardFooter>
          </Card>
        </GridItem>
        <GridItem xs={12} sm={6} md={3}>
          <Card>
            <CardHeader color="success" stats icon>
              <CardIcon color="success">
                <Icon>network_check</Icon>
              </CardIcon>
              <p className={classes.cardCategory}>Local IP</p>
              <h4  className={classes.cardTitle}>
               {local_ip_address}
              </h4>
              <br/>
            </CardHeader>
            <CardFooter stats>
              <div className={classes.stats}>
                <Success>
                  <Info />
                </Success>
                <a href="#pablo" onClick={e => e.preventDefault()}>
                  Now
                </a>
              </div>
            </CardFooter>
          </Card>
        </GridItem>
        <GridItem xs={12} sm={6} md={3}>
          <Card>
            <CardHeader color="success" stats icon>
              <CardIcon color="success">
              <Icon>keyboard_arrow_up</Icon>
              </CardIcon>
              <p className={classes.cardCategory}>Upstream</p>
              <h3 className={classes.cardTitle}>4069 <small>Kbps</small></h3> 
            </CardHeader>
            <CardFooter stats>
              <div className={classes.stats}>
              <Success>
                  <Info />
                </Success>
                Now
              </div>
            </CardFooter>
          </Card>
        </GridItem>
        <GridItem xs={12} sm={6} md={3}>
          <Card>
            <CardHeader color="success" stats icon>
              <CardIcon color="success">
                <Icon>keyboard_arrow_down</Icon>
              </CardIcon>
              <p className={classes.cardCategory}>Downstream</p>
              <h3 className={classes.cardTitle}>51176 <small>Kbps</small></h3>
            </CardHeader>
            <CardFooter stats>
              <div className={classes.stats}>
              <Success>
                  <Info />
                </Success>
                Now
              </div>
            </CardFooter>
          </Card>
        </GridItem>

      </GridContainer>

      <GridContainer>
      <GridItem xs={12} sm={12} md={12}>
        <Card>
          <CardHeader color="info">
            <h4 className={classes.cardTitleWhite}>LAN Network Scanner</h4>
            <p className={classes.cardCategoryWhite}>
              For scan local devices
            </p>
            <GridContainer>
            <GridItem xs={12} sm={6} md={1}  id = "scan_button"><Button  color="primary" round  onClick={() => scan_lan()}>Scan </Button> </GridItem>
            
            <GridItem xs={12} sm={6} md={3} id="loading_item" style={{visibility:"hidden"}} > <ReactLoading type={"bubbles"} color={"white"}  /> </GridItem>
            </GridContainer>
          </CardHeader>
          
          <CardBody>
          
          <table className="uk-table uk-table-hover uk-table-middle uk-table-divider">
              <thead>
                <tr>
                  <th className="uk-table-shrink" />
                  <th className="uk-table-shrink" />
                  <th className="uk-table-shrink">State</th>
                  <th>Name</th>
                  <th>Ip</th>
                  <th>Mac</th>
                </tr>
              </thead>
              <tbody>
                {isLoading ? <tr><td colSpan={6} className="uk-text-center"><em className="uk-text-muted">Loading...</em></td></tr>
                  : devicesArray.map((device, index) =>
                  
                      <ExpandableTable key={index} index={index + 1} user={device}  handleShow={ device.type == "MOBILE" ? handleShow_mobile : handleShow } />
                    )
                }
              </tbody>
            </table>
          </CardBody>
        </Card>
        
      </GridItem>
      </GridContainer>

      <Modal show={show} onHide={handleClose} size="xl"
      aria-labelledby="contained-modal-title-vcenter"
      centered ip = {ip}>
        <Modal.Header    closeButton >
              <Modal.Title>IP : {ip}</Modal.Title>              
              
        </Modal.Header>
        <Modal.Body> <Button color="info" round onClick={ () => get_infos(ip) }> Get Infos </Button>
        <h3> &nbsp; </h3>
          <Container fluid>
          <Row  >
                <Col xs={12} md={4}> 
                <h5> Cpu Load </h5>
                <ReactSpeedometer
                width={200}
                height={200}
                forceRender={true}
                maxValue={100}
                value={ cpu}
                needleColor="blue"
                startColor="green"
                segments={10}
                endColor="red"
              />
                
                
                </Col>
                <Col>
             <h5> Memory Load </h5>               
              <ReactSpeedometer
                width={200}
                height={200}
                forceRender={true}
                maxValue={ 100 }
                value={ memory_ == 0 ? 0 : Math.ceil(  (100 * ram) / ( memory_ * (1024 * 1024) )  ) }
                needleColor="blue"
                startColor="green"
                segments={10}
                endColor="red"
              />
              </Col> 
               <Col  > <p>Amount of memory (RAM) : { memory_  } GB  </p>
                       <p>Number of Process : { process_num }  </p>
                </Col> 



           
              </Row>
                
              <Tabs
                id="controlled-tab-example"
                activeKey={key}
                onSelect={(k) => setKey(k)}
              >
                <Tab eventKey="storage" title="Storage">
                <Table striped bordered hover>
                <GridItem xs={12} sm={6} md={3} id="loading_item_disk" style={{visibility:"hidden"}} > <ReactLoading type={"bubbles"} color={"black"}  /> </GridItem>
              <thead>
              <tr>                
                <th>Disk Name</th>
                <th>Total Space</th>
                <th>Used Space</th>
                <th>Percent</th>
              </tr>
            </thead>
            <tbody>
                {storage_flag ? storageArray.map((device, index) =>
                      <Storage name={device.name}  block={device.block}  total={device.total} used={device.used}  />
                    )
                  : null
                }
            </tbody>
              </Table>

                </Tab>
                <Tab eventKey="process" title="Processes">
                    <Table striped bordered hover relative >
                    <GridItem xs={12} sm={6} md={3} id="loading_item_process" style={{visibility:"hidden"}} > <ReactLoading type={"bubbles"} color={"black"}  /> </GridItem>
                      <thead>
                      <tr>                                        
                        <th>Process Name</th>                                           
                        <th>Run Type</th>
                      </tr>
                    </thead>
                    <tbody >
                        {processes_flag ? processesArray.map((device, index) =>
                              <Processes name={device.name} path = {device.path} parameter = {device.parameter}  run_type = {device.run_type} />
                            )
                          : null
                        }
                    </tbody>
                  </Table>

                </Tab>
                <Tab eventKey="devices" title="Devices">
                <Table striped bordered hover relative >
                <GridItem xs={12} sm={6} md={3} id="loading_item_device" style={{visibility:"hidden"}} > <ReactLoading type={"bubbles"} color={"black"}  /> </GridItem>
                      <thead>
                      <tr>   
                        <th>Index</th>                                     
                        <th>Device Name</th>                
                      </tr>
                    </thead>
                    <tbody >
                        {device_flag ? deviceArray.map((device, index) =>
                              <tr>
                                <th>{index + 1}</th>
                                 <th>{ device.name }</th> 
                                 
                                 </tr>  
                            )
                          : null
                        }
                    </tbody>
                  </Table>

                </Tab>
              </Tabs>


              
             


                
        

          </Container>
        </Modal.Body>
       
      </Modal>
 
      <Modal show={show_mobile} onHide={handleClose_mobile} size="xl"
      aria-labelledby="contained-modal-title-vcenter"
      centered ip = {ip}>
        <Modal.Header    closeButton >
          <Modal.Title>IP : {ip}</Modal.Title>              
        </Modal.Header>
        <Modal.Body> 
          <Container>
          <Row>
        {mobile_flag ? mobileArray.map((device, index) =>
                      <Mobile icon={device.icon} text={device.text} context={device.context}  />
                    )
                  : null
                }
          </Row>
          <h5></h5>
          <Row> <h5 className = "font-weight-bold" style={{ color:"purple" }} >Model Name&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;      :&nbsp;</h5><h5>{model} </h5></Row>
          <Row> <h5 className = "font-weight-bold" style={{ color:"purple" }} >Android Version&nbsp;&nbsp;&nbsp;   :&nbsp;</h5><h5>{andver} </h5> </Row>
          <Row> <h5 className = "font-weight-bold" style={{ color:"purple" }} >Up Time&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    &nbsp;&nbsp;&nbsp;&nbsp;       :&nbsp;</h5><h5>{uptime} </h5> </Row>
          </Container>
        </Modal.Body>


      </Modal>


    </div>
  );
}
