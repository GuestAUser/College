"""
                             [ DISCLAIMER ]
                             
This code and its associated logic were authored by GuestAUser(Lk10). 
Any use, distribution, or modification of this code MUST include proper credit to the original author. 
Failure to attribute the original author is a violation of intellectual property rights. 
By using this code, you agree to comply with these terms.

Thank you for respecting the work of the original creator.

"""

import matplotlib.pyplot as plt
import matplotlib.patches as patches
import random
from matplotlib.animation import FuncAnimation
import matplotlib.colors as mcolors

processes = []
for i in range(15):
    processes.append({
        'PID': f'P{i + 1}',
        'TC': i,
        'TE': random.randint(2, 10),
        'TE_original': None
    })

quantum = 3
current_time = 0
ready_queue = []
completed_processes = []
gantt_chart = []

processes.sort(key=lambda x: x['TC'])

# Algoritmo -> 'Round Robin Sechduling Algorithm'
while len(completed_processes) < len(processes):
    for process in processes:
        if process['TC'] <= current_time and process not in ready_queue and process not in completed_processes:
            ready_queue.append(process)
    if ready_queue:
        current_process = ready_queue.pop(0)
        if current_process['TE_original'] is None:
            current_process['TE_original'] = current_process['TE']
        exec_time = min(quantum, current_process['TE'])
        gantt_chart.append({'PID': current_process['PID'], 'start': current_time, 'end': current_time + exec_time})
        current_time += exec_time
        current_process['TE'] -= exec_time
        for process in processes:
            if current_time - exec_time < process['TC'] <= current_time and process not in ready_queue and process not in completed_processes:
                ready_queue.append(process)
        if current_process['TE'] > 0:
            ready_queue.append(current_process)
        else:
            current_process['TAT'] = current_time - current_process['TC']
            current_process['WT'] = current_process['TAT'] - current_process['TE_original']
            completed_processes.append(current_process)
    else:
        current_time = min(process['TC'] for process in processes if process not in completed_processes and process not in ready_queue)

plt.style.use('dark_background')
fig, ax = plt.subplots(figsize=(18, 10))
fig.patch.set_facecolor('#1E1E1E')

ax.set_xlabel('Time', fontsize=14, color='white')
ax.set_ylabel('Processes', fontsize=14, color='white')
ax.set_title('Round Robin Scheduling Com os Gráficos de Gantt', fontsize=16, color='white', pad=20)
ax.grid(True, axis='x', linestyle='--', alpha=0.5)

# tab20.colormap (colorway unico)
unique_pids = sorted(set(exec['PID'] for exec in gantt_chart))
cmap = plt.colormaps.get_cmap('tab20')
color_map = {pid: cmap(i % 20) for i, pid in enumerate(unique_pids)}

# y-axis(LABEL)
y_pos = {pid: idx for idx, pid in enumerate(unique_pids)}
ax.set_yticks(list(y_pos.values()))
ax.set_yticklabels(list(y_pos.keys()), fontsize=12, color='white')

time_text = ax.text(0.02, 0.95, '', transform=ax.transAxes, color='lightgreen', fontsize=12)
process_info = ax.text(0.02, 0.90, '', transform=ax.transAxes, color='lightgreen', fontsize=12)

bars = []
prev_bar = [None]  # Mutable Container;

def init():
    ax.set_xlim(0, max(exec['end'] for exec in gantt_chart) + 2)
    ax.set_ylim(-1, len(unique_pids))
    return []

def animate(frame):
    if prev_bar[0]:
        prev_bar[0].set_edgecolor('black')
        prev_bar[0].set_linewidth(1)

    exec_step = gantt_chart[frame]
    pid = exec_step['PID']
    start = exec_step['start']
    end = exec_step['end']
    duration = end - start
    color = color_map[pid]

    bar = ax.barh(y_pos[pid], duration, left=start, height=0.5, color=color, edgecolor='yellow')
    bar.patches[0].set_linewidth(2)

    bars.append(bar.patches[0])

    time_text.set_text(f'Current [Time]: {end}')
    process_info.set_text(f'Executing: {pid} | Start: {start} | End: {end} | Duration: {duration}')

    prev_bar[0] = bar.patches[0]

    return bars + [time_text, process_info]

ani = FuncAnimation(fig, animate, frames=len(gantt_chart), init_func=init,
                    interval=300, blit=True, repeat=False)

legend_patches = [patches.Patch(color=color_map[pid], label=pid) for pid in unique_pids]
legend = ax.legend(handles=legend_patches, bbox_to_anchor=(1.05, 1), loc='upper left', fontsize=10,
                   facecolor='#2E2E2E', edgecolor='white', title='Process Names', title_fontsize=12)
plt.setp(legend.get_texts(), color='white')
plt.setp(legend.get_title(), color='white')

plt.tight_layout()
plt.show()
