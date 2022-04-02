import './styles.css';
import React from 'react';

export function Avatar({ url, diameter }) {
  return (
    <div
      className={`avatar`}
      style={{
        width: diameter,
        height: diameter,
        backgroundImage: `url("${url}")`,
      }}
    ></div>
  );
}

Avatar.defaultProps = {
  diameter: '60px',
};
