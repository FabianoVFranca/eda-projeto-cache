import pandas as pd
import matplotlib.pyplot as plt
# import os
# print(f"Diretório atual: {os.getcwd()}")

df = pd.read_csv("dadosSaida2.txt", sep="|")

df.columns = df.columns.str.strip()

df['HitRatio'] = df['HitRatio'].str.replace(',', '.').astype(float)
df["Tamanho do Cache"] = df["Tamanho do Cache"].astype(float)

df_lfu = df[df["Tipo do Cache"] == "LFU"]
df_lru = df[df["Tipo do Cache"] == "LRU"]
df_fifo = df[df["Tipo do Cache"] == "FIFO"]

plt.figure(figsize=(10, 6))
plt.plot(df_lfu['Tamanho do Cache'], df_lfu['HitRatio'], marker='o', label='LFU')
plt.plot(df_lru['Tamanho do Cache'], df_lru['HitRatio'], marker='o', label='LRU')
plt.plot(df_fifo['Tamanho do Cache'], df_fifo['HitRatio'], marker='o', label='FIFO')
plt.title("Hit Ratio vs Tamanho do Cache para Diferentes Políticas")
plt.xlabel("Tamanho do Cache")
plt.ylabel("Hit Ratio (%)")
plt.legend()
plt.grid(True)
plt.savefig('../graficos/test.png', dpi=300, bbox_inches='tight')
plt.show()
plt.close()

