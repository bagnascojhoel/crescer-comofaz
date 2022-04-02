import './styles.css';
import React from 'react';

export function Container({column, className, children}) {
  return <div className={`
    container
    ${className}
    ${column && 'container--column'}
    `}>
    {children}
  </div>;
}
