import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
from matplotlib.ticker import ScalarFormatter


sns.set_style("whitegrid")
plt.rcParams['font.family'] = 'DejaVu Sans'  # Fonte mais legível

df = pd.read_csv("../../data/WorkloadFootprint.txt", sep="|")
df_dados = df.iloc[:47]
df.columns = df.columns.str.strip() 

x = df_dados['Quantidade de vezes que um elemento se repete na carga'].astype(int)
y = df_dados["Quantidade de elementos unicos que se repetem "].astype(int)

df = df_dados.sort_values(by="Quantidade de elementos unicos que se repetem ")

plt.figure(figsize=(12, 8))
ax = sns.barplot(
    data= df,
    x= x,
    y= y,
    color='#4C72B0', 
    edgecolor='black',
    linewidth= 0.8,
    orient='v'
)

# Títulos e eixos
plt.xlabel('Quantidade de repetições por elemento', fontsize=12)
plt.ylabel('Elementos únicos que se repetem (log)', fontsize=12)
plt.title('Distribuição de Frequências de Repetições na Carga de Trabalho ', 
          fontsize=14, pad=20, fontweight='bold')


ax.set_yscale('log')

plt.xticks(
    rotation= 45,
    ha='right',
    fontsize=9 
)

plt.text(
    0.30, 0.90,
    'Eixo Y em escala logarítmica (base 10)',
    transform=ax.transAxes,
    fontsize=10,
    ha='right',
    bbox=dict(facecolor='white', alpha=0.8, edgecolor='gray')
)


plt.ylim(0, y.max() * 1.05) 
plt.grid(axis='y', linestyle='--', alpha=0.7)
plt.tight_layout()

plt.savefig('../images/frequency_distribution_plot.png', dpi=300, bbox_inches='tight')
plt.show()
plt.close()
