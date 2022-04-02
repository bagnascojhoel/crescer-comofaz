import './styles.css';
import React from 'react';

export function Loader({ fullScreen, large, cover }) {
  return (
    <div
      className={`
        loader
        ${fullScreen && 'loader--full-screen'}
        ${cover && 'loader--cover'}
      `}
    >
      <div className={`loader__spinner ${large && 'loader--large'}`}></div>
    </div>
  );
}
