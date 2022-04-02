import './styles.css';
import React from 'react';

export function Alert({ success, error, children }) {
  return (
    <div
      className={`alert
      ${error && 'alert--error'}
      ${success && 'alert--success'}`}
    >
      {children}
    </div>
  );
}
