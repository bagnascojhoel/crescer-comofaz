import './styles.css';
import React from 'react';

export function LayoutRow({ className, style, alignStart, alignEnd, alignCenter, alignBottom, children }) {
  return (
    <div
      style={style}
      className={`
        layout-row
        ${className}
        ${alignCenter && 'layout-row--align-center'}
        ${alignStart && 'layout-row--align-start'}
        ${alignEnd && 'layout-row--align-end'}
        ${alignBottom && 'layout-row--align-bottom'}
      `}
    >
      {children}
    </div>
  );
}
