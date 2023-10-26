import styled from '@emotion/styled'

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

export const Inputs = styled('div')`
  padding: 1.5rem;

  display: flex;
  flex-direction: column;
  flex: 1;

  > input {
    width: 100%;

    font-size: 2rem;
    border: none;
    outline: none;
  }

  hr {
    width: 10%;
    height: 10px;

    background-color: ${({ theme }) => theme.palette.primary.main};

    border: none;
    margin-left: 0;

    text-align: left;
  }
`

export const TagContainer = styled.div`
  margin-bottom: 1rem;
  border-bottom: 1px solid ${({ theme }) => theme.palette.grey[500]};
`

export const TagInput = styled.input`
  border: none;
  outline: none;
  width: 100%;

  margin-top: 0.5rem;
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

  background-color: ${({ theme }) => theme.palette.grey[50]};

  ::-webkit-scrollbar-track {
    background-color: none;
  }
  ::-webkit-scrollbar {
    width: 5px;
    background-color: none;
  }
  ::-webkit-scrollbar-thumb {
    background-color: ${({ theme }) => theme.palette.primary.main};
    border-radius: 1rem;
  }
`
