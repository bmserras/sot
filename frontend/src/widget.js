import {LitElement, html, css, svg} from 'lit';

export class Widget extends LitElement {

    static get styles() {
        return css`
      :host {
        display: block;
        padding: 16px;
        max-width: 800px;
      }
    `;
    }

    static get properties() {
        return {
           status: { type: String },
           quantity: { type: Number }
        };
    }

    getProperties() {
        return [
            {
                'name': 'colorOk',
                'external_name': 'colorOk',
                'type': 'string',
                'default': 'green'
            },
            {
                'name': 'colorWarning',
                'external_name': 'colorWarning',
                'type': 'string',
                'default': 'orange'
            },
            {
                'name': 'colorError',
                'external_name': 'colorError',
                'type': 'string',
                'default': 'red'
            },
            {
                'name': 'quantity',
                'type': 'int'
            },
            {
                'name': 'warningThreshold',
                'external_name': 'warningThreshold',
                'type': 'int',
                'default': 30
            },
            {
                'name': 'criticalThreshold',
                'external_name': 'criticalThreshold',
                'type': 'int',
                'default': 15
            }
        ]
    }

    constructor() {
        super();

    }



    render() {
        return html`
    `;
    }


}

window.customElements.define('widget', Widget);
