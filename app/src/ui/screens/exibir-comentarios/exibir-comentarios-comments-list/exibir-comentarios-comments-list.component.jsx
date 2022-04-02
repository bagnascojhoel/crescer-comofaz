import './styles.css';
import React, { useEffect, useState } from 'react';
import {
  Container,
  HorizontalDivider,
  InputSelect,
  LayoutRow,
} from '../../../components';
import { ExibirComentariosLayoutComment } from '../exibir-comentarios-layout-comment/exibir-comentarios-layout-comment.component';

const ALL_DEFINICAO = 'TODOS';

export function ExibirComentariosCommentsList({
  onPublishResponse,
  comentarios,
  definicoesOptions,
  myName,
  myPhoto,
}) {
  const [options, setOptions] = useState(definicoesOptions);
  const [filtrados, setFiltrados] = useState(null);
  const [filter, setFilter] = useState(ALL_DEFINICAO);

  useEffect(() => {
    if (filter === ALL_DEFINICAO)
      setFiltrados(null);
    else {
      const result = comentarios.filter(
        ({ definicao }) => definicao === filter
      );
      setFiltrados(result);
    }
  }, [filter]);

  useEffect(() => {
    const all = {
      label: 'Todos',
      value: ALL_DEFINICAO,
    };
    setOptions([...options, all]);
  }, []);

  if (!comentarios || comentarios.length === 0)
    return (
      <h3 className="exibir-comentarios-comments-list--empty">
        Este post ainda não possui comentários
      </h3>
    );

  return (
    <>
      <Container>
        <LayoutRow className="exibir-comentarios-comments-list__header">
          <span className="exibir-comentarios-comments-list__total">
            {(filtrados ? filtrados : comentarios).length} comentários
          </span>
          <InputSelect
            onChange={setFilter}
            className="exibir-comentarios-comments-list__filter"
            options={options}
            value={filter}
            placeholder="Filtrar"
          />
        </LayoutRow>
      </Container>

      {(filtrados ? filtrados : comentarios).map(
        (comentario, i, arr) => (
          <div key={i}>
            <ExibirComentariosLayoutComment
              onPublishResponse={onPublishResponse}
              myName={myName}
              myPhoto={myPhoto}
              {...comentario}
            />
            <HorizontalDivider hidden={arr.length - 1 === i} smallGutter />
          </div>
        )
      )}
    </>
  );
}
