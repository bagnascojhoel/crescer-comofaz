import './styles.css';
import React from 'react';

export function Checkbox({ onCheck, checked, text }) {
  const _onCheck = () => onCheck(!checked);

  return (
    <button type="button" onClick={_onCheck} className="checkbox">
      <div
        className={`checkbox__circle ${checked && 'checkbox__circle--checked'}`}
      >
        <svg
          className="checkbox__circle__icon"
          width="8"
          height="8"
          viewBox="0 0 8 8"
          fill="none"
          xmlns="http://www.w3.org/2000/svg"
        >
          <path
            d="M3.43859 7.72092L0.128906 5.14251L1.06997 3.93479L3.10238 5.51811L6.45669 0.677826L7.71538 1.54976L3.43859 7.72092Z"
            fill="white"
          />
        </svg>
      </div>
      <span className="checkbox__text">{text}</span>
    </button>
  );
}
