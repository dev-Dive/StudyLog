import { atom } from 'recoil'

interface TagItem {
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
    thumbNailUrl: '',
    tags: [] as TagItem[],
    tagInput: '',
  },
})
