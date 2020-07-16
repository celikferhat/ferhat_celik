import React from "react";
import { Modal , Container , Row , Col} from 'react-bootstrap';
import {
    CircularProgressbar,
    CircularProgressbarWithChildren,
    buildStyles
  } from "react-circular-progressbar";
  import "react-circular-progressbar/dist/styles.css";
  import RadialSeparators from "components/ProgressBar/RadialSeparators";

const Mobile = ( props ) => (
   <Col>
    
    <Row>  { <img width="50" height="50"  src={require('assets/icons/mobile/'+props.icon +'.png' )} alt="typeicon" /> }  
    <h5 className="ml-3 mt-4"> {props.context} </h5>
    </Row>    
    
    
    </Col>
      
      
    
);

export default Mobile;