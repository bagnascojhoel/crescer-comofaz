import './styles.css';
import React from 'react';

export function VerticalSpacing({ smallGutter, largeGutter }) {
  return (
    <div
      className={`
        vertical-spacing
        ${smallGutter && 'vertical-spacing--small'}
        ${largeGutter && 'vertical-spacing--large'}
      `}
    ></div>
  );
}
