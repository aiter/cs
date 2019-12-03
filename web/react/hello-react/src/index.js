import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import './index.css';
//import App from './App';
import * as serviceWorker from './serviceWorker';

class Header extends Component {
    render() {
        return (
            <div>
                <h1> React aiter</h1>
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
