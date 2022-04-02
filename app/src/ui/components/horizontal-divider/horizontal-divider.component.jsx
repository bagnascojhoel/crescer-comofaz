import './styles.css';
import React from 'react';

export function HorizontalDivider({ noMarginBottom, hidden, smallGutter }) {
  return (
    <hr
      className={`
      horizontal-divider
      ${hidden && 'horizontal-divider--hidden'}
      ${smallGutter && 'horizontal-divider--small-gutter'}
      ${noMarginBottom && 'horizontal-divider--no-margin-bottom'}
      `}
    />
  );
}
