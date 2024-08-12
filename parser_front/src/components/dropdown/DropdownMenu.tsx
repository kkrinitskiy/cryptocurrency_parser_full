import {ChevronDownIcon} from "@heroicons/react/24/outline";
import {MenuItem} from "@/_lib/interfaces";
import Link from "next/link";

interface DropdownMenuProps {
    title: string;
    isOpen: boolean;
    onToggle: () => void;
    links: MenuItem[]
}

export function DropdownMenu({ title, isOpen, onToggle, links }: DropdownMenuProps) {
    return (
        <div
            onMouseEnter={onToggle}
            onMouseLeave={onToggle}
            className="relative flex flex-col items-center cursor-pointer">
            <div className="flex flex-row gap-1 items-center">
                <p>{title}</p>
                <ChevronDownIcon className="size-4"/>
            </div>
            <div className={`absolute top-full ${isOpen ? 'block' : 'hidden'}`}>
                <div className="bg-white border border-gray-300 shadow-lg mt-2 text-black rounded-xl">
                    <ul className="flex flex-col divide-y divide-[#f0f0f0]">
                        {links.map( link => {
                            return(
                                // <li key={link.title}>
                                    <Link key={link.title} className="flex flex-row py-2 px-5 items-center gap-3 hover:text-slate-700" href={link.link}>
                                        <link.icon className="size-5"/>
                                        {link.title}
                                    </Link>
                                // </li>
                            )
                            }
                        )}
                    </ul>
                </div>
            </div>
        </div>
    );
}