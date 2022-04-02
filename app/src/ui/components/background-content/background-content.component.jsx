import './styles.css';
import React from 'react';

export function BackgroundContent({ className, children }) {
  return <div className={`background-content ${className}`}>{children}</div>;
}
