import './styles.css';
import React from 'react';
import { Controller, useForm } from 'react-hook-form';
import { useHistory } from 'react-router-dom';
import {
  Container,
  InputRating,
  InputSelect,
  InputText,
  InputTextArea,
  InputMaterialList,
  InputPassoList,
  MainButton,
  Title,
  InputImage,
  LayoutRow,
  LayoutLabel,
  InputCategoriaList,
} from '../../components';
import {
  usePrivadoPostApi,
  useFileSend,
  useToastMessageManager,
  useApiErrorMessageManager,
} from '../../../hooks';
import duracaoOptions from '../../../duracao-options';

export function CadastrarPostScreen() {
  const { push } = useHistory();
  const apiErrorMessage = useApiErrorMessageManager();
  const { register, handleSubmit, errors, control } = useForm();

  const { postar } = usePrivadoPostApi();

  const { convertFormData } = useFileSend();
  const toastMessage = useToastMessageManager();

  const onCadastrar = async (body) => {
    body.materiais = body.materiais.map(({ value }) => value);
    const formData = convertFormData(body, body.passos, body.imagemCapa);

    const response = await postar({ formData });

    if (response.error) {
      apiErrorMessage.send(response.error);
    } else {
      toastMessage.send('Como faz cadastrado com sucesso');
      push(`/post/${response}`);
    }
  };

  return (
    <Container>
      <Title>Criar Como Faz</Title>

      <form onSubmit={handleSubmit(onCadastrar)}>
        <InputText
          register={register({ required: true })}
          name="titulo"
          label="Título"
          hasError={errors.titulo}
          placeholder="Adicione um título"
          errorMessage="Precisamos de um título para este ComoFaz"
        />

        <InputTextArea
          register={register({ required: true })}
          name="descricao"
          label="Descrição"
          placeholder="Descreva o ComoFaz"
          hasError={errors.descricao}
          errorMessage="Precisamos de uma descrição"
        />

        <InputCategoriaList control={control} name="idTags" />

        <InputMaterialList
          control={control}
          register={register({ required: true })}
          name="materiais"
        />

        <InputPassoList
          control={control}
          register={register({ required: true })}
          name="passos"
        />

        <Controller
          as={InputRating}
          rules={{ required: true }}
          name="dificuldade"
          control={control}
          label="Dificuldade"
          defaultValue={null}
          hasError={errors.dificuldade}
          errorMessage="Precisamos saber o quão dificil isto é"
        />

        <Controller
          as={InputSelect}
          name="duracaoMinutos"
          control={control}
          options={duracaoOptions}
          rules={{ required: true }}
          defaultValue={null}
          hasError={errors.duracaoMinutos}
          errorMessage="Precisamos saber quanto tempo isto demora"
          placeholder="Tempo de execução"
          label="Tempo de execução"
        />

        <LayoutRow className="cadastrar-post__capa">
          <LayoutLabel>Escolher foto de capa</LayoutLabel>

          <InputImage name="imagemCapa" control={control} defaultValue={null} />
        </LayoutRow>

        <InputText
          label="Dica"
          register={register()}
          name="dica"
          hasError={errors.dica}
          placeholder="Dica"
        />

        <MainButton className="cadastrar-post__submit-button" submit>
          publicar Como Faz
        </MainButton>
      </form>
    </Container>
  );
}
