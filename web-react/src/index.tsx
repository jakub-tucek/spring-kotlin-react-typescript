import * as React from 'react'
import {render} from 'react-dom'

import PageOne from './modules/one'
import PageTwo from './modules/two'

// Initializes react if element with id if it exists
function initReact(reactElement: any, elementId: string) {
    const elem = document.getElementById(elementId);
    if (elem) {
        render(reactElement, elem);
    } else {
        console.log('Element with given id not found', elementId);
    }
}

initReact(<PageOne/>, 'react-one');
initReact(<PageTwo/>, 'react-two');
