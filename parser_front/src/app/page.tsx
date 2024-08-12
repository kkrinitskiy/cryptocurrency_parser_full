import Image from "next/image";
import {CurrencyCard} from "@/components/currency_card/CurrencyCard";

export default function Home() {
  return (
    <main className="grid grid-cols-3 gap-10">
    <CurrencyCard />
      <CurrencyCard />
      <CurrencyCard />
      <CurrencyCard />
      <CurrencyCard />
      <CurrencyCard />
      <CurrencyCard />
      <CurrencyCard />
      <CurrencyCard />
      <CurrencyCard />
    </main>
  );
}
