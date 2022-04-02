import './styles.css';
import React from 'react';

export function LayoutLabel({ htmlFor, className, children }) {
  return htmlFor ? (
    <label htmlFor={htmlFor} className={`layout-label ${className}`}>
      {children}
    </label>
  ) : (
    <span className={`layout-label ${className}`}>{children}</span>
  );
}
