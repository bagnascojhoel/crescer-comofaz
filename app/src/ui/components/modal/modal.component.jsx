import './styles.css';
import closeIcon from '../../../assets/close.svg';
import React, { useEffect } from 'react';
import { LayoutRow } from '../layout-row/layout-row.component';
import { IconButton } from '../icon-button/icon-button.component';

export function Modal({ onClose, show, bottom, className, children }) {
  const handleStopClick = (e) => e.stopPropagation();
  const handleStopTouchMove = (e) => e.preventDefault();

  useEffect(() => {
    const body = document.querySelector('body');
    if (show) body.classList.add('modal-open');
    else body.classList.remove('modal-open');
  }, [show]);

  return (
    <div
      onClick={onClose}
      onTouchMove={handleStopTouchMove}
      className={`
        modal-container
        ${bottom && 'modal-container--bottom'}
        ${show && 'modal-container--visible'}
      `}
    >
      <div
        onClick={handleStopClick}
        className={`
          modal
          ${show && 'modal--visible'}
          ${bottom && 'modal--bottom'}
          ${className}
        `}
      >
        <LayoutRow className="modal__header" alignEnd>
          <IconButton onClick={onClose} iconUrl={closeIcon} />
        </LayoutRow>
        {children}
      </div>
    </div>
  );
}
