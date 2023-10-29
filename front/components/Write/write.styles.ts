import styled from '@emotion/styled'

const S = {
  WriteWrapper: styled.div`
    width: 100%;
    height: 100vh;
    display: flex;
  `,
  Title: styled.div`
    font-size: large;
  `,

  LeftSide: styled.div`
    display: flex;
    flex-direction: column;
    flex: 1;
    justify-content: space-between;
  `,

  Inputs: styled('div')`
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
  `,
  TagContainer: styled.div`
    margin-bottom: 1rem;
    border-bottom: 1px solid ${({ theme }) => theme.palette.grey[500]};
  `,
  TagInput: styled.input`
    border: none;
    outline: none;
    width: 100%;

    margin-top: 0.5rem;
  `,
  Tags: styled.div`
    margin-top: 1.1rem;
  `,
  MarkDownView: styled.div`
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
  `,
}

export default S
