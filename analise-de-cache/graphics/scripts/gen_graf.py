import pandas as pd
import matplotlib.pyplot as plt
# import os
# print(f"Diretório atual: {os.getcwd()}")

df = pd.read_csv("../../data/RandomizedWorkloadOutput.txt", sep="|")

df.columns = df.columns.str.strip()

df['HitRatio'] = df['HitRatio'].str.replace(',', '.').astype(float)
df["Tamanho do Cache"] = df["Tamanho do Cache"].astype(float)

df_lfu = df[df["Tipo do Cache"] == "LFU"]
df_lru = df[df["Tipo do Cache"] == "LRU"]
df_fifo = df[df["Tipo do Cache"] == "FIFO"]


# gráfico comparativo de todas as políticas.
plt.figure(figsize=(10, 6))
plt.plot(df_lfu['Tamanho do Cache'], df_lfu['HitRatio'], marker='o', label='LFU')
plt.plot(df_lru['Tamanho do Cache'], df_lru['HitRatio'], marker='o', label='LRU', color='orange')
plt.plot(df_fifo['Tamanho do Cache'], df_fifo['HitRatio'], marker='o', label='FIFO', color='green')
plt.title("Hit Ratio vs Capacidade do Cache para Diferentes Políticas")
plt.xlabel("Capacidade do Cache")
plt.ylabel("Hit Ratio (%)")
plt.legend()
plt.grid(True)
plt.savefig('../images/all_cache_comparative_plot.png', dpi=300, bbox_inches='tight')
plt.show()
plt.close()

# grafico unitário de LFU
plt.figure(figsize=(10, 6))
plt.plot(df_lfu['Tamanho do Cache'], df_lfu['HitRatio'], marker='o', label='LFU')
plt.title("Hit Ratio vs Capacidade do Cache")
plt.xlabel("Capacidade do Cache")
plt.ylabel("Hit Ratio (%)")
plt.legend()
plt.grid(True)
plt.savefig('../images/unit_lfu_plot.png', dpi=300, bbox_inches='tight')
plt.show()
plt.close()

# grafico unitário de LRU
plt.figure(figsize=(10, 6))
plt.plot(df_lru['Tamanho do Cache'], df_lru['HitRatio'], marker='o', label='LRU', color='orange')
plt.title("Hit Ratio vs Capacidade do Cache")
plt.xlabel("Capacidade do Cache")
plt.ylabel("Hit Ratio (%)")
plt.legend()
plt.grid(True)
plt.savefig('../images/unit_lru_plot.png', dpi=300, bbox_inches='tight')
plt.show()
plt.close()

# grafico unitário de FIFO
plt.figure(figsize=(10, 6))
plt.plot(df_fifo['Tamanho do Cache'], df_fifo['HitRatio'], marker='o', label='FIFO', color='green')
plt.title("Hit Ratio vs Capacidade do Cache")
plt.xlabel("Capacidade do Cache")
plt.ylabel("Hit Ratio (%)")
plt.legend()
plt.grid(True)
plt.savefig('../images/unit_fifo_plot.png', dpi=300, bbox_inches='tight')
plt.show()
plt.close()
