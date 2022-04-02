import './styles.css';
import React from 'react';

export function Title({ className, children }) {
  return <h1 className={`title ${className}`}>{children}</h1>;
}
