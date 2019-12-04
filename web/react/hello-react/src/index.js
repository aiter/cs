import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import './index.css';
//import App from './App';
import * as serviceWorker from './serviceWorker';

class Header extends Component {
    render() {
        const name = 'aiter'
        const className = 'header'
        return (
            <div>
                <h1> React aiter {name} {(function () { return 'is good'})()}</h1>

                <div className={className}></div>
            </div>
        )
    }
}

ReactDOM.render(
    <Header />,
    document.getElementById('root')
)

// ReactDOM.render(<App />, document.getElementById('root'));

//http://huziketang.mangojuice.top/books/react/lesson6

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
