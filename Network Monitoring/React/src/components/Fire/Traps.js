import React from 'react';
import { render } from 'react-dom';
import { slideDown, slideUp } from '../../assets/jss/anim';
import '../../assets/css/style.css';
import Button from "components/CustomButtons/Button.js";
import { Row } from 'react-bootstrap';
import firebase from "firebase";
import { Toast } from 'react-bootstrap';



export default class Processes extends React.Component {

 


  constructor(props) {
    super(props);
    this.state = {show: false};
      
    const config = {
      apiKey: "AIzaSyBkkuDKbYZUKijEwVEcirW1-xXQTgUpqv8",
      authDomain: "snmptrap-6f82c.firebaseapp.com",
      databaseURL: "https://snmptrap-6f82c.firebaseio.com",
      projectId: "snmptrap-6f82c",
      storageBucket: "snmptrap-6f82c.appspot.com",
      messagingSenderId: "997265395510",
      appId: "1:997265395510:web:a3355b6d3db64471c1ed97"
    };
    // Initialize Firebase
    firebase.initializeApp(config);
  
    var starCountRef = firebase.database().ref('Traps');

    
    var first_run = true;
    var commentsRef = firebase.database().ref('Traps/');
    commentsRef.limitToLast(1).on('child_added', (data) => {
      if(!first_run){
      this.setState({ HostName: data.val().DeviceName}); 
      this.setState({ show: true });
    }
    first_run = false;
    });

    
    /*

    starCountRef.limitToLast(1).on('value', (snapshot) => {       
   

       for(var key in snapshot.val()){        
        this.setState({ HostName: snapshot.val()[key].DeviceName});
        this.setState({ show: true });
       }
       
       
    });
    */
  }

 


  render() {
    return(
 <div>
   <Toast show={this.state.show}  onClose={() => this.setState({show:false}) } >
  <Toast.Header>
    <img src="holder.js/20x20?text=%20" className="rounded mr-2" alt="" />
    <strong className="mr-auto"> High CPU usage </strong>
    <small>Now</small>
  </Toast.Header>
    <Toast.Body>{this.state.HostName}</Toast.Body>
</Toast>


 </div>)
  }
}






