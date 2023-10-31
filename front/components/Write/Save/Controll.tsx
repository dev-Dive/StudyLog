import { ChangeEvent, useState } from 'react'
import {
  Typography,
  OutlinedInput,
  MenuItem,
  FormControl,
  InputLabel,
  Select,
  Button,
  TextField,
  colors,
} from '@mui/material'
import { SelectChangeEvent } from '@mui/material/Select'
import styled from '@emotion/styled'
import { useRecoilState, useRecoilValue } from 'recoil'
import { postState } from '@/states/postAtom'
import { savePost } from '@/api/Post/savePost'

const { grey } = colors

interface ControllModalProps {
  closeModal: () => void
}

export default function Controll({ closeModal }: ControllModalProps) {
  const [post, setPost] = useRecoilState(postState)
  const [studyName, setStudyName] = useState('10')
  const [uploadedFileName, setUploadedFileName] = useState<string | null>(null)
  // const currentPostState = useRecoilValue(postState)

  const handleChange = (event: SelectChangeEvent) => {
    const selectedStudyName = event.target.value as string
    setStudyName(selectedStudyName)

    // setPost((prevState) => ({
    //   ...prevState,
    //   studyName: selectedStudyName,
    // }))
  }

  const handleUpload = (event: ChangeEvent<HTMLInputElement>) => {
    const file = event.target.files && event.target.files[0]

    if (!file) {
      console.log('No file selected')
      return
    }

    const formattedName =
      file.name.length > 40 ? file.name.substring(0, 37) + '...' : file.name
    setUploadedFileName(formattedName)

    const reader = new FileReader()

    reader.onload = (e) => {
      const dataUrl = e.target?.result as string

      setPost((prevState) => ({
        ...prevState,
        thumbNailUrl: dataUrl,
      }))
    }

    reader.onerror = (e) => {
      console.log('File reading error:', e)
    }

    reader.readAsDataURL(file)
  }

  const handleSubTitleChange = (event: ChangeEvent<HTMLInputElement>) => {
    const newSubTitle = event.target.value

    setPost((prevState) => ({
      ...prevState,
      subTitle: newSubTitle,
    }))
  }

  const handleSvaePost = async () => {
    await savePost(post)
  }

  return (
    <>
      <S.Container>
        <S.Title variant="h4">추가 정보를 입력해주세요</S.Title>

        <FormControl sx={{ width: 614, marginBottom: 8 }}>
          <InputLabel id="demo-simple-select-label">
            스터디를 선택해주세요
          </InputLabel>
          <Select
            labelId="demo-simple-select-required-label"
            id="demo-simple-select-required"
            value={studyName}
            label=" 스터디를 선택해주세요"
            onChange={handleChange}
          >
            <MenuItem value={10}>Ten</MenuItem>
            <MenuItem value={20}>Twenty</MenuItem>
            <MenuItem value={30}>Thirty</MenuItem>
          </Select>
        </FormControl>

        <div>
          <FormControl
            variant="outlined"
            fullWidth
            sx={{ width: 614, marginBottom: 8 }}
          >
            <OutlinedInput
              value={uploadedFileName || ''}
              placeholder={
                post.thumbNailUrl === ''
                  ? '선택된 썸네일을 변경하고 싶으면 변경해주세요'
                  : '선택된 파일이 없습니다.'
              }
              readOnly
              startAdornment={
                <input
                  accept="image/*"
                  style={{ display: 'none' }}
                  id="contained-button-file"
                  type="file"
                  onChange={handleUpload}
                />
              }
              endAdornment={
                <label htmlFor="contained-button-file">
                  <Button
                    component="span"
                    sx={{
                      width: 100,
                      backgroundColor: grey[500],
                      color: 'white',
                      '&:hover': {
                        backgroundColor: grey[700],
                      },
                    }}
                  >
                    이미지 업로드
                  </Button>
                </label>
              }
            />
          </FormControl>
        </div>
        <TextField
          id="outlined-multiline-static"
          label="스터디 글 부제를 입력해주세요"
          multiline
          rows={2}
          value={post.subTitle}
          onChange={handleSubTitleChange}
          sx={{
            width: 614,
          }}
        />
        <S.Buttons>
          <Button variant="outlined" onClick={closeModal}>
            취소
          </Button>
          <Button variant="contained" onClick={handleSvaePost}>
            글 저장하기
          </Button>
        </S.Buttons>
      </S.Container>
    </>
  )
}

const S = {
  Container: styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
  `,
  Title: styled(Typography)`
    margin-bottom: 90px;
  `,
  InputContainer: styled.div`
    display: flex;
    flex-direction: column;
    gap: 30px;
  `,
  Buttons: styled.div`
    display: flex;
    width: 614px;
    gap: 20px;
    margin-top: 20px;
    justify-content: flex-end;
  `,
}
