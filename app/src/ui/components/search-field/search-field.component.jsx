import './styles.css';
import React from 'react';
import searchIcon from '../../../assets/search.svg';
import { Button, Form, Rate } from 'antd'

export function SearchField({ onChange, value, placeholder, handleFilter }) {
  return (
    <div className="search-field">
      <Form onFinish={handleFilter}>
        <Form.Item>
          <Button type='link' htmlType='submit'>
            <img className="search-field__icon" src={searchIcon} alt=""/>
          </Button>
        </Form.Item>

        <Form.Item name='titulo' placeholder={placeholder}>
          <input className="search-field__input" onChange={onChange} value={value} />
        </Form.Item>

        <Form.Item name='dificuldade' label='Dificuldade'>
          <Rate allowClear/>
        </Form.Item>
      </Form>
    </div>
  );
}
