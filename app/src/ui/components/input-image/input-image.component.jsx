import './styles.css';
import React, { useEffect, useState } from 'react';
import attachmentIcon from '../../../assets/attachment.svg';
import closeIcon from '../../../assets/close.svg';
import { IconButton } from '../icon-button/icon-button.component';
import { useToastMessageManager } from '../../../hooks';
import { useController } from 'react-hook-form';

const validateSize = (file) => {
  const SIZE_LIMIT = 1000000;
  return file.size <= SIZE_LIMIT;
};

const defaultImage = { preview: '', raw: null };

export function InputImage({ control, name, defaultValue, small }) {
  const toastMessage = useToastMessageManager();
  const inputFileRef = React.createRef();

  const { field } = useController({
    name,
    control,
    defaultValue: { ...defaultImage },
  });

  const [image, setImage] = useState({ ...defaultImage });

  const handleRemove = () => {
    setImage({ ...defaultImage });
  };

  const handleOpenDialog = () => {
    inputFileRef.current.click();
  };

  const handleChange = (e) => {
    if (e.target.files.length) {
      const file = e.target.files[0];

      if (!validateSize(file)) {
        toastMessage.send('A imagem precisa ter no máximo 1 MB');
        return;
      }

      setImage({
        preview: URL.createObjectURL(file),
        raw: file,
      });
    }
  };

  useEffect(() => {
    if (defaultValue) setImage({ raw: null, preview: defaultValue });
  }, []);

  useEffect(() => {
    field.onChange(image.raw);
  }, [image]);

  return (
    <div className={`input-image ${small && 'input-image--small'}`}>
      {!image.raw && (
        <input
          ref={inputFileRef}
          onChange={handleChange}
          name={name}
          type="file"
          accept=".jpg,.png,.jpeg"
          style={{ display: 'none' }}
        />
      )}
      {!image.preview && (
        <div onClick={handleOpenDialog} className="input-imag-placeholder">
          <img src={attachmentIcon} alt="" />
        </div>
      )}

      {image.preview && (
        <div className="input-image-preview">
          <img
            src={image.preview}
            alt=""
            className="input-image-preview__image"
          />
          <IconButton
            className="input-image-preview__icon"
            iconUrl={closeIcon}
            onClick={handleRemove}
          />
        </div>
      )}
    </div>
  );
}
