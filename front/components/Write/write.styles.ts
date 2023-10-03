import styled from '@emotion/styled'
import { color, text } from '@/styles/theme'

export const WriteWrapper = styled.div`
  width: 100%;
  height: 100vh;

  display: flex;
`

export const Title = styled.div`
  font-size: large;
`

export const Inputs = styled.div`
  width: 50%;

  padding: 1.5rem;

  > input {
    width: 100%;

    border: none;
    outline: none; // 포커스 없애기

    font-size: ${text.h1['font-size']};
    font-weight: ${text.h1['font-weight']};
    line-height: ${text.h1['line-height']};
  }

  hr {
    width: 10%;
    height: 10px;

    background-color: ${color.mainColor};

    border: none;
    margin-left: 0;

    text-align: left;
  }
`

export const TagContainer = styled.div`
  margin-bottom: 1rem;
  border-bottom: 1px solid ${color.mainColor};
`

export const TagInput = styled.input`
  border: none;
  outline: none;
  width: 100%;

  margin-top: 0.5rem;

  font-size: ${text.normal['font-size']};
  font-weight: ${text.normal['font-weight']};
  line-height: ${text.normal['line-height']};
`

export const Tags = styled.div`
  margin-top: 1.1rem;
`

export const MarkDownView = styled.div`
  width: 50%;

  background-color: ${color.gray2};
`
