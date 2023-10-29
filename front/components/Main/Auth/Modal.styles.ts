import styled from '@emotion/styled'

const S = {
  Modal: styled.div`
    padding: 1.5rem;
    width: 30rem;
    height: 24rem;
    border-radius: 0.313rem;
    display: flex;
    flex-direction: column;
    background-color: ${({ theme }) => theme.palette.grey[200]};

    h1 {
      margin-top: 4.8rem;
      margin-bottom: 5rem;
    }

    > div:nth-of-type(1) {
      display: flex;
      justify-content: flex-end;
    }

    > div:nth-of-type(2) {
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      > div {
        width: 90%;
        > div {
          display: flex;
          align-items: center;
          justify-content: center;
        }
      }
    }

    input {
      height: 2.875rem;
      padding-left: 0.8rem;
      border: 0;
      font-size: 1rem;
      width: 70%;
    }
  `,
  EmailBtn: styled.button`
    background: ${({ theme }) => theme.palette.primary.light};
    border: 0;
    padding: 0.8rem 1rem;
    font-size: 1rem;
    height: 2.875rem;
    color: ${({ theme }) => theme.palette.common.white};
    font-weight: 700;
    width: 25%;
  `,
  ToRegister: styled.div`
    display: flex;
    justify-content: flex-end;

    button {
      background-color: transparent;
      border: 0;
      color: ${({ theme }) => theme.palette.primary.main};

      :hover {
        text-decoration: underline;
      }
    }
  `,
}

export default S
