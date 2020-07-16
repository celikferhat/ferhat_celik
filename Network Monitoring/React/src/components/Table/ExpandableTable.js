import React from 'react';
import { render } from 'react-dom';
import { slideDown, slideUp } from '../../assets/jss/anim';
import '../../assets/css/style.css';
import Button from "components/CustomButtons/Button.js";


var deskription ;



function formatDate(str) {
  return str;
}

function capitalize(str) {
  return str.split(' ').map(s => {
    return s.charAt(0).toUpperCase() + s.substr(1);
  }).join(' ');
}



export default class UserTableRow extends React.Component {

  constructor(props) {
    super(props);
    this.state = {expanded: false , sysdesk : ""};
  }

  toggleExpander = (e) => {
    if (e.target.type === 'checkbox') return;

    if (!this.state.expanded) {
      this.setState(
        { expanded: true },
        () => {
          if (this.refs.expanderBody) {
            slideDown(this.refs.expanderBody);
          }
        }
      );
    } else {
      slideUp(this.refs.expanderBody, {
        onComplete: () => { this.setState({ expanded: false }); }
      });
    }
  }

 get_basic_info(ip){      
    fetch("http://127.0.0.1:5000/snmp/sysdesk/" + ip).then(res => res.text() ).then( (data) => {
      this.setState({sysdesk: data });
    } ).catch(error => {
      console.error('Error:', error);
      alert("Snmp service unavaible!");
      
    });
    fetch("http://127.0.0.1:5000/snmp/sysname/" + ip).then(res => res.text() ).then( (data) => {
      this.setState({sysname: data });
    } ).catch(error => {
      console.error('Error:', error);
      alert("Snmp service unavaible!");
      
    });
    fetch("http://127.0.0.1:5000/snmp/sysuptime/" + ip).then(res => res.text() ).then( (data) => {
      this.setState({sysuptime: data });
    } ).catch(error => {
      console.error('Error:', error);
      alert("Snmp service unavaible!");
      
    });
    fetch("http://127.0.0.1:5000/snmp/sysifnum/" + ip).then(res => res.text() ).then( (data) => {
      this.setState({sysifnum: data });
    } ).catch(error => {
      console.error('Error:', error);
      alert("Snmp service unavaible!");
      
    });
    
    
       // this.setState({sysdesk: this.get_basic_info(ip) }); 
  }



  render() {
    const { user } = this.props;
    return [
      <tr key="main" onClick={this.toggleExpander}>
        <td></td>
        <td className="uk-text-nowrap">{this.props.index}.</td>
        <td>{capitalize(user.status)} </td>
        <td>{capitalize(user.name )}</td>
        <td>{capitalize(user.ip)} </td>
        <td>{formatDate(user.mac)}</td>		
      </tr>,
      this.state.expanded && (
        <tr className="expandable" key="tr-expander">
          <td className="uk-background-muted" colSpan={6}>
            <div ref="expanderBody" className="inner uk-grid">
              <div className="uk-width-1-5 uk-text-center">
      { <img width="400" height="400" className=" " src={require('assets/icons/'+user.icon+'.png' )} alt="typeicon" /> }
              </div>
              <div className="uk-width-1-5">
                <h3>Type: {capitalize(user.type)} </h3>
                <p> <b>Status:</b> {capitalize(user.status)}  </p>
                <p> <b>IP Address : </b>{capitalize(user.ip)}</p>
                <p><b> Mac Address : </b>{capitalize(user.mac)}</p>
                <p><b> Vendor : </b>{capitalize(user.vendor)}</p>
				        <p> <b>Name : </b>{capitalize(user.name)}</p>
				        <p> <b>Model : </b>{capitalize(user.model)}</p>
				        <p> <b>OS : </b>{capitalize(user.os)}</p>
				
              </div>
              <div className="uk-width-2-5">
              <h3> SNMP Informations </h3><Button color="primary"  round  onClick={() => this.get_basic_info(user.ip) }> Get Basic Infos </Button>

              <p> <b>Sys Description:</b> {this.state.sysdesk}  </p>
              <p> <b>Sys Name:</b> {this.state.sysname}  </p>
              <p> <b>Sys Uptime:</b> {this.state.sysuptime}  </p>
              <p> <b>Sys if Number:</b> {this.state.sysifnum}  </p>
                
              </div>
              <div className="uk-width-1-5">
              <h3> &nbsp; </h3> <Button color="primary"  round  onClick={() => this.props.handleShow(user.ip) }> Get Complex Infos </Button>
              </div>
            </div>
          </td>
        </tr>
        


      )
    ];
  }
}






