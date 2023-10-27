import { atom } from 'recoil'

type TagItem = {
  id: number
  text: string
}

export const postState = atom({
  key: 'postState',
  default: {
    content: '',
    title: '',
    subTitle: '',
    studyId: 0,
    thumNailUrl: '',
    tags: [] as TagItem[],
    tagInput: '',
  },
})
