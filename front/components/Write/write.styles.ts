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

export const LeftSide = styled.div`
  display: flex;
  flex-direction: column;
  flex: 1;
  justify-content: space-between;
`

export const Inputs = styled.div`
  padding: 1.5rem;

  display: flex;
  flex-direction: column;
  flex: 1;

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
  border-bottom: 1px solid ${color.gray4};
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
  /* width: 50%; */
  flex: 1;
  height: calc(100 * var(--vh));
  padding-left: 1rem;
  overflow: auto;

  background-color: ${color.gray2};

  ::-webkit-scrollbar-track {
    background-color: none;
  }
  ::-webkit-scrollbar {
    width: 5px;
    background-color: none;
  }
  ::-webkit-scrollbar-thumb {
    background-color: ${color.mainColor};
    border-radius: 1rem;
  }
`
