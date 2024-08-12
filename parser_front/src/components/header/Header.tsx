"use client"
import {DropdownMenu} from "@/components/dropdown/DropdownMenu";
import {useState} from "react";
import {MenuItem} from "@/_lib/interfaces";
import {BriefcaseIcon, StarIcon, EnvelopeIcon, ChatBubbleOvalLeftEllipsisIcon, PlayCircleIcon, QuestionMarkCircleIcon} from "@heroicons/react/24/outline";
import Link from "next/link";

export function Header() {
    const [openMenu, setOpenMenu] = useState<string | null>(null);

    const handleToggle = (menu: string) => {
        setOpenMenu(openMenu === menu ? null : menu);
    };


    const usefulMenuLinks: MenuItem[] = [
        {
        title: "Блог",
        link: "/",
        icon: ChatBubbleOvalLeftEllipsisIcon
        },
        {
            title: "Видео",
            link: "/",
            icon: PlayCircleIcon
        },
        {
            title: "FAQ",
            link: "/",
            icon: QuestionMarkCircleIcon
        },
    ]

    const aboutMenuLinks: MenuItem[] = [
        {
            title: "Отзывы",
            link: "/",
            icon: StarIcon
        },
        {
            title: "Контакты",
            link: "/",
            icon: EnvelopeIcon
        },
        {
            title: "Партнерам",
            link: "/",
            icon: BriefcaseIcon
        },
    ]

    return (
        <header className={`grid grid-cols-12 items-center bg-black justify-items-start
            text-white p-5 px-10 rounded-b-3xl
            sticky
            /*fixed top-0 left-0 right-0 z-50*/
            transition-all duration-300`}>
            <Link
                className="col-start-1 col-end-2 flex flex-row items-center gap-2"
                href={"/"}>
                <svg className="text-white size-32" width="10px" height="10px" viewBox="0 0 512 512"
                     xmlns="http://www.w3.org/2000/svg">
                    <path fill="#ffffff"
                          d="M337.2 56.85c2.6 4.01 5 10.08 9.6 14.39-6 14.4-22.9 25.94-42.7 25.66l16.1 15.6 25.9-14.9 20.3 17 19.4-9.5 19.6 7.5L419.6 98c-20.7-7-20.6-23.84-28.8-36.5-1.6-2.43-5.2-3.51-9.8-3.59-7.6-.14-18 2.48-26.1 6.27-7-2.17-10.1-5.32-17.7-7.33zM299.8 112c-23.3 14.8-47.7 29.3-74 28.8-16.4-7.3-39.8-12.2-66.8-15.9 3.5 5.1 4.6 10.5 4.4 16.2-1.3.1-2.6.1-3.9.2-11.5-3.3-27-4.9-50.4-2.9 6.3 1.9 11.8 4 15.4 6.8-34.78 6.1-72.74 15-87.27 47.8-4.11 9.3-6.83 18.8-8.6 28.4l-9.09 25.7 6.61-4.5c-.98 16.6 0 33.6.86 50.6l-6.43 35 7.44-11.9c.24 8.8.23 17.6-.33 26.3l21.74-56.2 11.13 71.5c1.66 11.9-3.98 22.2-7.88 33.2l7.69-.6-.1 10.5-10.02 30.7 9.86-4.7-.12 22.6c18.75 13.3 39.44 10.7 61.01.7l7.3-100.7c4.9-10.8 11.1-21.3 16.5-30.4 5.8-10 10.6-19.8 11.4-25.1l17.8 2.8c-3.4 13.9-9.6 24.8-16.1 35.5 23.5-1.2 44.8-5.7 64.1-13.9-.8-6.9-3.2-15.1-3.6-21l18-1.2c1.1 6.7 3.1 18.5 4.5 26 2.7 15.2 6.3 35.1 9.9 54.8 6.6 36.7 12.8 70.2 13.7 75.1 21 8.4 45.2 6.2 60.1 1.9l-2.9-55.5 7.2 3.6-7.9-15.9-.2-3.6 8.1 6-9.8-38.6-.2-3.9 9 2.2-10.7-35.7-1.1-20.5c12.9-4.1 25.1-8.3 37-12.8-2.3-2.5-4.6-5.1-6.8-7.9-9.3-11.3-14.4-26.5-12.3-40.6 1.1-7.1 4.1-15.4 11.5-18.5 9.1-3.8 24.1-7.4 30.4 1.7 22.6 31.9 40.5 45.2 51.4 47.8 5.5 1.3 9.3.6 13.3-1.7 14.3-10.4 14.5-15.1 14-32.2-7.2-18.7-12.9-37.7-18.6-56.7l14.3 2.9c-7.5-9-16.3-14.5-22-27.9-2.5-7.8-5.1-15.6-7.9-23.4l-6 12.5-27.8-10.7-22.8 11.3-19.7-16.5-31.5 18.2zm82.5 58.8l26.8 15.4c-3.4 3.8-6.6 7.9-15.3 6-9.3-2.1-12-13-11.5-21.4zm107.1 16c-3.4 24-9.8 51-21.7 70.3-4.8 7.8-10.6 14.4-18.2 18.7-7.5 4.4-16.9 5.9-26.3 3.6-18.4-4.3-37.1-20.6-59.8-52-6.5-1.5-10.8.9-11.6 6.2-1 6.9 2.6 19.4 8.4 26.5h.1c20.9 25.7 41.6 36.7 59.7 38.6 18.1 1.9 34.4-5.1 47.3-18.4 20-20.5 30.9-56 22.1-93.5zm-39.9 126c-9.8 3.5-20.4 4.9-31.4 3.8-2.5-.3-5.1-.7-7.6-1.2-1.5 17-5.5 33.2-14.4 42.3-11.1 11.4-47.3 6.7-47.3 6.7-10.9-2.5-11.4 52.2 12.5 43.3 0 0 51.2-14.6 66.3-33.5 3.8-4.8 7.2-10.7 10-17.3l3.2 10.3.7-20.6c.9-2.8 1.8-5.8 2.6-8.7l7 9.2zm-224.3 23.5l-6.6 1.8-1 113.4c9.8 5.5 19.1 3.6 28.2-.7-1.7-9.8-6.8-37.4-12.7-70.5-2.8-15.5-5.5-30.5-7.9-44zm-36.7 9.1c-13 .9-44.9 6.6-46.3 10.1l-1.8 95.3c18.2 10.1 35.7 5.3 53.2.2z"/>
                </svg>
                <div
                    className="text-3xl font-extrabold bg-gradient-to-br from-yellow-400 via-yellow-100 to-yellow-400 bg-clip-text text-transparent">
                    <p>Crypto</p>
                    <p>Currency</p>
                    <p>App</p>
                </div>
            </Link>
            <div className="flex flex-row gap-x-10 col-start-4 col-end-8 justify-self-center">
                <DropdownMenu links={usefulMenuLinks} title={"Полезное"} isOpen={openMenu === 'menu1'}
                              onToggle={() => handleToggle('menu1')}/>
                <DropdownMenu links={aboutMenuLinks} title={"О нас"} isOpen={openMenu === 'menu2'}
                              onToggle={() => handleToggle('menu2')}/>
            </div>
            <div className="flex flex-row gap-x-10 text-center font-extrabold text-xl col-start-9 col-end-13">
                <p className="border border-white cursor-pointer w-full py-2 px-10 rounded-full hover:text-black hover:bg-white
                transition-all duration-300">
                    Войти
                </p>
                <div className="hover:bg-white rounded-full transition-all duration-300 cursor-pointer">
                    <p className="border border-white w-full cursor-pointer py-2 px-10 rounded-full
                    bg-gradient-to-bl from-purple-500 to-red-300
                    bg-clip-text text-transparent">
                        Регистрация
                    </p>
                </div>
            </div>
        </header>
    );
}