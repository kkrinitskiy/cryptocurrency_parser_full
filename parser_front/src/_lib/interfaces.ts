import React, {ComponentType} from "react";

export interface MenuItem {
    title: string,
    link: string,
    icon: ComponentType<React.SVGProps<SVGSVGElement>>;

}