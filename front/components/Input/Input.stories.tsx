import type { Meta, StoryFn } from '@storybook/react'

import Input from './Input'

const meta = {
  component: Input,
  title: 'Common/Input',
  parameters: {
    design: {
      type: 'figma',
      url: 'figmaURL',
    },
  },
} as Meta<typeof Input>

export default meta

const Template: StoryFn<typeof Input> = (args) => <Input {...args} />

export const Default = Template.bind({})

Default.args = {}

export const Secondary = Template.bind({})
Secondary.args = {
  placeHolder: 'text',
}
