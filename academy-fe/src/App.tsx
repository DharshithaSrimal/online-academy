// src/App.tsx
import React from 'react';
import './App.css';
import Courses from './components/Courses';

const App: React.FC = () => {
    return (
        <div className="App">
            <Courses />
        </div>
    );
};

export default App;
