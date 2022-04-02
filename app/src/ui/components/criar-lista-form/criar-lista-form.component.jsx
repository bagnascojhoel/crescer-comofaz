import React from 'react';
import { Controller, useForm } from 'react-hook-form';
import { InputText, LayoutRow, Checkbox, MainButton } from '..';
import {
  useApiErrorMessageManager,
  usePrivadoListaApi,
  useToastMessageManager,
} from '../../../hooks';

export function CriarListaForm({ onCriada, mapper }) {
  const apiErrorMessage = useApiErrorMessageManager();
  const toastMessage = useToastMessageManager();
  const { cadastrarLista } = usePrivadoListaApi();

  const { handleSubmit, register, control, reset, errors, watch } = useForm({
    defaultValues: {
      assunto: '',
      isPrivado: true,
    },
  });

  const handleCriarLista = async ({ assunto, isPrivado }) => {
    const response = await cadastrarLista({ assunto, isPrivado });
    reset();

    if (response.error) apiErrorMessage.send(response.error);
    else {
      toastMessage.send('Lista criada');
      if (mapper) onCriada(mapper(response));
      else onCriada(response);
    }
  };

  return (
    <>
      <form onSubmit={handleSubmit(handleCriarLista)}>
        <InputText
          register={register({ required: true })}
          name="assunto"
          label="Assunto"
          placeholder="Top 10 formas de trocar pneu"
          hasError={errors.assunto}
          errorMessage="Precisamos de um assunto para a lista"
        />

        <LayoutRow>
          <Controller
            control={control}
            name="isPrivado"
            render={({ onChange, value }) => (
              <Checkbox
                onCheck={onChange}
                checked={value}
                text="Esta lista é privada"
              />
            )}
          />

          <MainButton submit small>
            criar lista {watch('isPrivado') ? 'privada' : 'pública'}
          </MainButton>
        </LayoutRow>
      </form>
    </>
  );
}
