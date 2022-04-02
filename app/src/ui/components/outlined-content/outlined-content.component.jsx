import './styles.css';
import React from 'react';

export function OutlinedContent({ style, className, children }) {
  return (
    <div style={style} className={`outlined-content ${className}`}>
      <span className="outlined-content__text">{children}</span>
    </div>
  );
}
