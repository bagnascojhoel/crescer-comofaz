import './styles.css';
import React from 'react';

export function Subtitle({ className, children }) {
  return <h2 className={`subtitle ${className}`}>{children}</h2>;
}
