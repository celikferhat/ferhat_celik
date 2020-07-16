import React from "react";
import { Modal , Container , Row , Col} from 'react-bootstrap';
import {
    CircularProgressbar,
    CircularProgressbarWithChildren,
    buildStyles
  } from "react-circular-progressbar";
  import "react-circular-progressbar/dist/styles.css";
  import RadialSeparators from "components/ProgressBar/RadialSeparators";

const Storage = ( props ) => (
   <tr>
      <td className="mt-3"> {props.name} </td>
      <td className="mt-3"> { parseInt((parseInt(props.total) * parseInt(props.block)) / (1024 * 1024 )) } Mbyte</td>
      <td className="mt-3"> { parseInt((parseInt(props.used) * parseInt(props.block) ) / (1024 * 1024 )) } Mbyte</td>

      <td > <div style={{ width: 60, height: 60 }}  >
            <CircularProgressbarWithChildren
                value={ Math.ceil(  (props.used * 100) / props.total ) }
                text={`${Math.ceil(  (props.used * 100) / props.total )}%`}
                strokeWidth={10}
                styles={buildStyles({
                strokeLinecap: "butt"
                })}  >
                <RadialSeparators
                count={12}
                style={{
                    background: "#fff",
                    width: "2px",
                    // This needs to be equal to props.strokeWidth
                    height: `${10}%`
                }}
                />
            </CircularProgressbarWithChildren>    
            </div>
      </td>
    </tr>
);

export default Storage;