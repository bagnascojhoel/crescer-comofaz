import React, { useState, useEffect } from 'react';
import { Controller, useForm } from 'react-hook-form';
import { useHistory, useParams } from 'react-router-dom';
import {
  Container,
  InputRating,
  InputSelect,
  InputText,
  InputTextArea,
  InputMaterialList,
  InputPassoList,
  MainButton,
  InputImage,
  LayoutRow,
  LayoutLabel,
  Loader,
  InputCategoriaList,
  Title,
} from '../../components';
import {
  usePrivadoPostApi,
  useFileSend,
  useToastMessageManager,
  useApiErrorMessageManager,
} from '../../../hooks';
import duracaoOptions from '../../../duracao-options';

export function EditarPostScreen() {
  const { push } = useHistory();
  const toastMessage = useToastMessageManager();
  const apiErrorMessage = useApiErrorMessageManager();

  const { register, handleSubmit, errors, control } = useForm();

  const { editarPost } = usePrivadoPostApi();
  const { buscarPost } = usePrivadoPostApi();
  const { convertFormData } = useFileSend();

  const { idPost } = useParams();
  const [post, setPost] = useState();

  useEffect(() => {
    (async () => {
      const response = await buscarPost(idPost);

      if (response.error) apiErrorMessage.send(response.error);
      else setPost(response);
    })();
  }, []);

  const onEdit = async (body) => {
    body.materiais = body.materiais.map(({ value }) => value);
    const formData = convertFormData(body, body.passos, body.imagemCapa);

    const response = await editarPost({ idPost, formData });

    if (response.error) {
      apiErrorMessage.send(response.error);
    } else {
      toastMessage.send('Como faz editado com sucesso');
      push(`/post/${response}`);
    }
  };

  if (!post) return <Loader fullScreen large />;

  return (
    <Container>
      <Title>Editar Como Faz</Title>
      <form onSubmit={handleSubmit(onEdit)}>
        <InputText
          register={register({ required: true })}
          name="titulo"
          label="Título"
          hasError={errors.titulo}
          placeholder="Adicione um título"
          errorMessage="Precisamos de um título para este ComoFaz"
          defaultValue={post.titulo}
        />

        <InputTextArea
          register={register({ required: true })}
          name="descricao"
          label="Descrição"
          placeholder="Descreva o ComoFaz"
          hasError={errors.descricao}
          errorMessage="Precisamos de uma descrição"
          defaultValue={post.descricao}
        />

        <InputCategoriaList
          control={control}
          name="idTags"
          defaultValue={post.tags.map((t) => t.idCategoria)}
        />

        <InputMaterialList
          control={control}
          register={register({ required: true })}
          name="materiais"
          defaultValue={post.materiais}
        />

        <InputPassoList
          control={control}
          register={register({ required: true })}
          name="passos"
          defaultValue={post.passos}
        />

        <Controller
          as={InputRating}
          rules={{ required: true }}
          control={control}
          label="Dificuldade"
          name="dificuldade"
          hasError={errors.dificuldade}
          errorMessage="Precisamos saber o quão dificil isto é"
          defaultValue={post.dificuldade}
        />

        <Controller
          as={InputSelect}
          name="duracaoMinutos"
          control={control}
          options={duracaoOptions}
          rules={{ required: true }}
          hasError={errors.duracaoMinutos}
          errorMessage="Precisamos saber quanto tempo isto demora"
          placeholder="Tempo de execução"
          label="Tempo de execução"
          defaultValue={post.duracaoMinutos}
        />

        <LayoutRow className="cadastrar-post__capa">
          <LayoutLabel>Escolher foto de capa</LayoutLabel>

          <InputImage
            control={control}
            name="imagemCapa"
            defaultValue={post.imagemCapa}
          />
        </LayoutRow>

        <InputText
          label="Dica"
          register={register()}
          name="dica"
          placeholder="Dica"
          defaultValue={post.dica}
        />

        <MainButton className="cadastrar-post__submit-button" submit>
          confirmar novos dados
        </MainButton>
      </form>
    </Container>
  );
}
