// noinspection DuplicatedCode

import {LitElement, html, css, svg} from 'lit';

export class CabinWidget extends LitElement {

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
            name: {type: String},
            battery: {type: Number},
            hasWarning: {type: Boolean}
        };
    }

    constructor() {
        super();
        this.name = 'Cabin name';
        this.battery = 0;
        this.hasWarning = false;
    }

    svgCircleTemplate() {
        return svg`
      <circle 
        cx="50" 
        cy="50" 
        r="40" 
        stroke="black" 
        stroke-width="3" 
        fill=${this.hasWarning ? "red" : "green"} 
        @click=${this._dispatchCircle}
      />
    `;
    }

    _dispatchCircle() {
        console.log('DISPATCH CIRCLE');
        this.dispatchEvent(new CustomEvent('circle-click'));
    }

    svgRectangleTemplate() {
        return svg`
      <rect 
        x=100 
        width="300" 
        height="100" 
        @click=${this._dispatchRect}
      />
    `;
    }

    _dispatchRect() {
        console.log('DISPATCH RECT');
        this.dispatchEvent(new CustomEvent('rect-click'));
    }

    svgImage() {
        return html`
            <svg height="100" width="500">
                ${this.svgCircleTemplate()}
                ${this.svgRectangleTemplate()}
            </svg>
        `;
    }

    render() {
        return html`
            <p>Circle and rectangle example</p>
            ${this.svgImage()}
        `;
    }

}

window.customElements.define('cabin-widget', CabinWidget);
