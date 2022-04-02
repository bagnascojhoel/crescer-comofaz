import './styles.css';
import React from 'react';

export function ExibirPostPasso({ position, text, photo }) {
  return (
    <div className="exibir-post-passo">
      <h3 className="exibir-post-passo__title">Passo {position}</h3>
      <div className="exibir-post-passo__content">
        {photo && (
          <div
            className="exibir-post-passo-photo"
            style={{ backgroundImage: `url(${photo})` }}
          ></div>
        )}
        <p
          style={!photo ? { marginLeft: 0 } : {}}
          className="exibir-post-passo-text"
        >
          {text}
        </p>
      </div>
    </div>
  );
}
