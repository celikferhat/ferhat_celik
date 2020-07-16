import React from 'react';
import { render } from 'react-dom';
import { slideDown, slideUp } from '../../assets/jss/anim';
import '../../assets/css/style.css';
import Button from "components/CustomButtons/Button.js";
import { Row } from 'react-bootstrap';





function formatDate(str) {
  return str;
}

function capitalize(str) {
  return str.split(' ').map(s => {
    return s.charAt(0).toUpperCase() + s.substr(1);
  }).join(' ');
}



export default class Processes extends React.Component {

  constructor(props) {
    super(props);
    this.state = {expanded: false};
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


  render() {
    
    return [
      <tr key="main" onClick={this.toggleExpander}>               
        <td>{ this.props.name } </td>
        <td>{ this.props.run_type }</td>     	
      </tr>,
      this.state.expanded && (
        <tr className="expandable" key="tr-expander">
          <td className="uk-background-muted" colSpan={6}>
            <div ref="expanderBody" className="inner uk-grid">            
            <div className="uk-width-5-5">
            <Row> <h5 style={{ color:"purple" }}>Path &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp; </h5><h5>{ this.props.path }    </h5> </Row>
			<Row> <h5 style={{ color:"purple" }}>Parameters :&nbsp; </h5><h5>{ this.props.parameter } </h5> </Row>
                </div>      
            </div>
          </td>
        </tr>
        


      )
    ];
  }
}






