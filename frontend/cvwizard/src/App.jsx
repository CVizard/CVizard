import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

const links = [
  { name: 'Open roles', href: '#' },
  { name: 'Internship program', href: '#' },
  { name: 'Our values', href: '#' },
  { name: 'Meet our leadership', href: '#' },
]
const stats = [
  { name: 'Offices worldwide', value: '12' },
  { name: 'Full-time colleagues', value: '300+' },
  { name: 'Hours per week', value: '40' },
  { name: 'Paid time off', value: 'Unlimited' },
]

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
    <center>
      <div className="w-screen absolute -top-10 -z-10 blur-sm">
        <img src="https://external-preview.redd.it/xdMgRNsy5mWmlwxLjkOFNUdwxxo5OFhbxWRhZXEsl1Q.jpg?auto=webp&s=a186c25783b2d1a4ab99ac4d44426b2cbfc18c4f"/>
      </div>
      
      <div className="w-screen lg:mx-0">
        <center>
        {/* <div class="flex flex-row w-40 absolute right-321 top-60"> */}
        <div class="flex flex-row h-40 w-fit">
          <img class="basis-1/3 transform -scale-x-100" src='https://i.gifer.com/origin/93/93da1b13d0f8fa632a17bf2931b1b333_w200.gif'/>
           <img class="-mb-8 basis-1/3" src='https://coc.guide/static/imgs/og/troop/wizard.png'/>
          <img class="basis-1/3" src='https://i.gifer.com/origin/93/93da1b13d0f8fa632a17bf2931b1b333_w200.gif'/>
        </div>
        </center>
        <h1 className="text-9xl font-bold tracking-tight text-white sm:text-6xl">CVizard</h1>
          <label
            class="flex justify-center w-80 h-32 px-4 transition bg-white mt-10 border-1 border-gray-300 border-dashed rounded-md appearance-none cursor-pointer hover:border-gray-400 focus:outline-none">
            <span class="flex items-center space-x-2 ">
                <svg xmlns="http://www.w3.org/2000/svg" class="w- h-6 text-gray-600" fill="none" viewBox="0 0 24 24"
                    stroke="currentColor" stroke-width="2">
                    <path stroke-linecap="round" stroke-linejoin="round"
                        d="M7 16a4 4 0 01-.88-7.903A5 5 0 1115.9 6L16 6a5 5 0 011 9.9M15 13l-3-3m0 0l-3 3m3-3v12" />
                </svg>
                <span class="font-medium text-gray-600">
                    Drop files to Attach or
                    <span> </span>
                    <span class="text-blue-600 underline">browse</span>
                </span>
            </span>
            <input type="file" name="file_upload" class="hidden" />
      </label>
      
      </div>
      
    </center>
    </>
  )
}

export default App
