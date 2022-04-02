export function useFileSend() {
  const convertFormData = (request, files, file) => {
    const formData = new FormData();

    const blobFile = new Blob([file], {
      type: 'multipart/form-data',
    });

    formData.append('imagem', blobFile);
    delete request[file];

    if (files)
      files.forEach((file) => {
        const blobFile = new Blob([file.foto], {
          type: 'multipart/form-data',
        });

        formData.append('imagens', blobFile);

        delete file.foto;
      });

    const blobRequest = new Blob([JSON.stringify(request)], {
      type: 'application/json',
    });

    formData.append('request', blobRequest);

    return formData;
  };

  return { convertFormData };
}
