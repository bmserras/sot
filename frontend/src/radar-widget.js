import {LitElement, html, css, svg} from 'lit';

const cinemometer = svg`
    <g
         id="Cinemometer"
         transform="translate(120.46553,35.592088)">
        <path
           class="cls-11"
           d="M 969,1067.72 H 806.47 V 965.09 L 846.8,965 v -15.87 h 81.81 V 965 l 40.4,0.11 z M 815.2,1059 h 145.08 v -85.21 l -40.39,-0.11 v -15.83 h -64.37 v 15.83 l -40.32,0.11 z"
           id="camera"
           style="fill:#f98e00" />
        <path
           class="cls-11"
           d="m 889.21,1048.82 a 37.67,37.67 0 1 1 37.67,-37.67 37.71,37.71 0 0 1 -37.67,37.67 z m 0,-66.61 a 28.95,28.95 0 1 0 28.95,28.94 29,29 0 0 0 -28.95,-28.94 z"
           id="lens"
           style="fill:#f98e00" />
        <path
           class="cls-11"
           d="m 886,1031.91 v -8.72 a 14.64,14.64 0 0 0 14.62,-14.63 h 8.73 A 23.38,23.38 0 0 1 886,1031.91 Z"
           id="reflection"
           style="fill:#f98e00" />
      </g>
`;

const cabin = svg`
    <g
         id="Cabin"
         transform="translate(120.46553,35.592088)"
         style="display:inline">
        <path
           class="cls-10"
           d="M 1002.21,1275 H 778.79 a 12.3,12.3 0 0 1 -12.28,-12.29 V 918.42 a 12.3,12.3 0 0 1 12.28,-12.28 h 223.42 a 12.3,12.3 0 0 1 12.28,12.28 v 344.29 A 12.3,12.3 0 0 1 1002.21,1275 Z M 778.79,917.35 a 1.07,1.07 0 0 0 -1.07,1.07 v 344.29 a 1.06,1.06 0 0 0 1.07,1.07 h 223.42 a 1.06,1.06 0 0 0 1.07,-1.07 V 918.42 a 1.07,1.07 0 0 0 -1.07,-1.07 z"
           id="door"
           style="fill:#131e56" />
        <path
           class="cls-10"
           d="M 1009.93,917.35 H 771.07 a 6.41,6.41 0 0 1 -4.63,-10.86 L 801.75,870 h 177.5 l 35.31,36.49 a 6.41,6.41 0 0 1 -4.63,10.86 z M 782.39,906.14 H 998.61 L 974.5,881.22 h -168 z"
           id="head"
           style="fill:#131e56" />
        <rect
           class="cls-10"
           x="970.88"
           y="1119.23"
           width="17.450001"
           height="79.75"
           rx="7"
           id="handle"
           style="fill:#131e56" />
      </g>
`;

const radar = (scale) => html`
    <svg
            width="${scale * 100}"
            height="${scale * 160}"
            viewBox="0 0 100 160"
            id="svg659"
            xmlns="http://www.w3.org/2000/svg">
        <g
                id="Layer1"
                transform="translate(-60.459947,-64.118949)">
            <g
                    id="Radar"
                    transform="matrix(0.3972782,0,0,0.39506173,-291.17462,-293.64583)">
                ${cabin}
                ${cinemometer}
            </g>
        </g>
    </svg>
`;

const title = (title) => html`
    <h3>${title}</h3>
`;

export class RadarWidget extends LitElement {

    static styles = css`
        :host {
            border-width: 10px;
            border-style: solid;
            border-radius: 20px;
        }
    `;

    static properties = {
        name: {type: String},
        scale: {type: Number},
        warning: {type : Number}
    };

    constructor() {
        super();
        this.name = "Default Name"
        this.scale = 1;
        this.warning = 0;
    }

    render() {
        const greyBorder = html`<style> :host { border-color: grey; } </style>`;
        const redBorder = html`<style> :host { border-color: red; } </style>`;
        const yellowBorder = html`<style> :host { border-color: yellow; } </style>`;
        const greenBorder = html`<style> :host { border-color: green; } </style>`;
        let borderStyle;
        switch (this.warning) {
            case 1:
                borderStyle = greenBorder; break;
            case 2:
                borderStyle = yellowBorder; break;
            case 3:
                borderStyle = redBorder; break;
            default:
                borderStyle = greyBorder;
        }
        return html`
            ${borderStyle}
            <div style="text-align:center;">
                ${title(this.name)}
                ${radar(this.scale)}
            </div>
        `;
    }
}

window.customElements.define('radar-widget', RadarWidget);